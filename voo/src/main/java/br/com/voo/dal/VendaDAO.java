package br.com.voo.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.voo.model.Passagem;
import br.com.voo.model.Venda;
import br.com.voo.util.FactoryConexao;

public class VendaDAO {

	Connection cnn;
	PassagemDAO passagemDAO;
	ClienteDAO clienteDAO;

	public VendaDAO(Connection cnn) {
		this.cnn = FactoryConexao.getConnection();
	}
	public boolean incluir(Venda venda) throws SQLException{
		
		String sql = " INSERT INTO venda (horario,desconto,tipoDePagamento,"
				+ "situacao,codigo_passagem,codigo_cliente)";
		
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setDate(1, Date.valueOf(venda.getHorario()));
		ps.setDouble(2, venda.getDesconto());
		ps.setString(3, venda.getTipoDePagamento());
		ps.setString(4, venda.getSituacao());
		ps.setLong(5, venda.getCodigoPassagem());
		ps.setLong(6, venda.getCodigoCliente());
		
		ps.execute();
		
		return true;
	}
	
	public boolean alterar(Venda venda)throws SQLException{
		
		String sql = "UPDATE venda SET horario = ?,"
				+ "desconto = ?,"
				+ "tipo_de_pagamento = ?,"
				+ "situacao = ?,"
				+ "codigo_passagem = ?,"
				+ "codigo_cliente = ? "
				+ "WHERE codigo = ?";
		
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setDate(1, Date.valueOf(venda.getHorario()));
		ps.setDouble(2, venda.getDesconto());
		ps.setString(3, venda.getTipoDePagamento());
		ps.setString(4, venda.getSituacao());
		ps.setLong(5, venda.getCodigoPassagem());
		ps.setLong(6, venda.getCodigoCliente());
		ps.setLong(7, venda.getId());
		
		
		ps.execute();
		
		return true;
	}
	
	public boolean excluir(Venda venda)throws SQLException{
		
		String sql = "DELETE FROM venda WHERE codigo = ?";
		
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setLong(1, venda.getId());
		
		ps.execute();
		
		return true;
	}
	
	public Venda consultar(Venda venda) throws SQLException, Exception{
		
		String sql = "SELECT * FROM venda WHERE codigo = ?";
		
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setLong(1, venda.getId());
		
		ResultSet rs = ps.executeQuery();
		Venda retorno = new Venda();
		
		if(rs.next()){
			retorno = new Venda(
					rs.getLong("codigo"),
					rs.getDate("horario").toLocalDate(),
					rs.getDouble("desconto"),
					rs.getString("tipo_de_pagamento"),
					rs.getString("situacao"),
					new Passagem(), //TODO adicionar passagem aqui
					clienteDAO.consultar(rs.getLong("cliente_codigo")));
		}
	    
		return retorno;
	}
	
	public List<Venda> listar() throws SQLException, Exception{
		
		String sql = "SELECT * FROM venda";
		
		PreparedStatement ps = cnn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
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
