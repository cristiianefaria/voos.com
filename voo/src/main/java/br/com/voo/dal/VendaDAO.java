package br.com.voo.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.com.voo.model.Passagem;
import br.com.voo.model.Venda;
import br.com.voo.util.FactoryConexao;

public class VendaDAO {

	Connection cnn;
	PassagemDAO passagemDAO;
	ClienteDAO clienteDAO;
	
	final static Logger log = Logger.getLogger(VendaDAO.class.getName());

	public VendaDAO() {
		this.cnn = FactoryConexao.getConnection();
	}
	
	
	public Venda incluir(Venda venda) throws Exception{
		
		String sql = " INSERT INTO compra (horario,desconto,tipo_pagamento,"
				+ "situacao,removido,codigo_passagem,codigo_cliente) VALUES (?,?,?,?,?,?,?) RETURNING codigo";
		
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setDate(1, Date.valueOf(venda.getHorario()));
		ps.setDouble(2, venda.getDesconto());
		ps.setString(3, venda.getTipoDePagamento());
		ps.setString(4, venda.getSituacao());
		ps.setBoolean(5, venda.getRemovida());
		ps.setLong(6, venda.getCodigoPassagem());
		ps.setLong(7, venda.getCodigoCliente());

		ResultSet rs = ps.executeQuery();
		log.info(ps.toString());
		
		venda = new Venda();

		rs.next();
		venda.setId(rs.getLong("codigo"));
		
		return consultar(venda);
	}
	
	public boolean alterar(Venda venda)throws SQLException{
		
		String sql = "UPDATE compra SET horario = ?,"
				+ "desconto = ?,"
				+ "tipo_pagamento = ?,"
				+ "situacao = ?,"
				+ "codigo_passagem = ?,"
				+ "codigo_cliente = ?,"
				+ "removido  = ? "
				+ "WHERE codigo = ?";
		
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setDate(1, Date.valueOf(venda.getHorario()));
		ps.setDouble(2, venda.getDesconto());
		ps.setString(3, venda.getTipoDePagamento());
		ps.setString(4, venda.getSituacao());
		ps.setLong(5, venda.getCodigoPassagem());
		ps.setLong(6, venda.getCodigoCliente());
		ps.setBoolean(7, venda.getRemovida());
		ps.setLong(8, venda.getId());
		
		
		ps.execute();
		log.info(ps.toString());
		
		return true;
	}
	
	public boolean remover(Venda venda)throws SQLException{
		String sql = "UPDATE compra SET "
				+ "removido = ? "
				+ "WHERE codigo = ?";
		
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setBoolean(1, venda.getRemovida());
		ps.setLong(2, venda.getId());
		
		ps.execute();
		log.info(ps.toString());
		
		return true;
	}
	
	public boolean excluir(Venda venda)throws SQLException{
		
		String sql = "DELETE FROM compra WHERE codigo = ?";
		
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setLong(1, venda.getId());
		
		ps.execute();
		log.info(ps.toString());
		
		return true;
	}
	
	public Venda consultar(Venda venda) throws SQLException, Exception{
		
		String sql = "SELECT * FROM compra WHERE codigo = ? and removido = false";
		
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setLong(1, venda.getId());
		
		ResultSet rs = ps.executeQuery();
		log.info(ps.toString());
		
		Venda retorno = new Venda();
		
		if(rs.next()){
			retorno = new Venda(
					rs.getLong("codigo"),
					rs.getDate("horario").toLocalDate(),
					rs.getDouble("desconto"),
					rs.getString("tipo_pagamento"),
					rs.getString("situacao"),
					passagemDAO.consultarPassagem(rs.getLong("codigo_passagem")), 
					clienteDAO.consultar(rs.getLong("cliente_codigo")));
		}
	    
		return retorno;
	}
	
	public List<Venda> listar() throws SQLException, Exception{
		
		String sql = "SELECT * FROM compra WHERE removido = false";
		
		PreparedStatement ps = cnn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		log.info(ps.toString());
		
		ArrayList<Venda> vendas = new ArrayList<Venda>();
		
		while(rs.next()){
			Venda venda = new Venda(
					rs.getLong("codigo"),
					rs.getDate("horario").toLocalDate(),
					rs.getDouble("desconto"),
					rs.getString("tipo_de_pagamento"),
					rs.getString("situacao"),
					new Passagem(), //TODO adicionar passagem aqui
					clienteDAO.consultar(rs.getLong("cliente_codigo")));
			vendas.add(venda);
		}
	    
		return vendas;
	}

	
	
}
