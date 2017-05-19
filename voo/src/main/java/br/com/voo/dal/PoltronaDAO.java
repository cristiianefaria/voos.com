package br.com.voo.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.com.voo.model.Aeronave;
import br.com.voo.model.Poltrona;
import br.com.voo.util.FactoryConexao;

public class PoltronaDAO {
	
	final static Logger log = Logger.getLogger(PoltronaDAO.class.getName());

    public boolean salvar(Poltrona poltrona) throws SQLException{
    	
    	String sql = "INSERT INTO poltrona (descricao,valor,classe,detalhes,codigo_aeronave) VALUES(?,?,?,?,?)";
    	
    	Connection cnn = FactoryConexao.getConnection();
    	PreparedStatement ps =  cnn.prepareStatement(sql);
    	ps.setString(0, poltrona.getDescricao());
    	ps.setDouble(2, poltrona.getValor());
    	ps.setString(3, poltrona.getClasse());
    	ps.setString(4, poltrona.getDetalhes());
    	ps.setLong(5, poltrona.getCodigoAeronave());
    	
    	ps.execute();
    	
    	ps.close();
    	cnn.close();
    	
    	return true;
    }
    
	public boolean alterar(Poltrona poltrona) throws SQLException{
		String sql = "UPDATE poltrona SET descricao = ?,"
				+ " valor = ?, classe = ?, detalhes = ? WHERE codigo = ?";
		
		Connection cnn = FactoryConexao.getConnection();
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setString(1, poltrona.getDescricao());
		ps.setDouble(2, poltrona.getValor());
		ps.setString(3, poltrona.getClasse());
		ps.setString(4, poltrona.getDetalhes());
		ps.setLong(5, poltrona.getId());
		
		ps.execute();
		ps.close();
		cnn.close();
		
		return true;
	}

	public boolean excluir(Poltrona poltrona)throws SQLException {
		String sql = "DELETE FROM poltrona where codigo = ?";
		
		Connection cnn = FactoryConexao.getConnection();
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setLong(1, poltrona.getId());
		
		ps.execute();
		
		ps.close();
		cnn.close();
		return true;
	}
	
	public boolean excluirPorAeronave(Poltrona poltrona)throws SQLException {
		String sql = "DELETE FROM poltrona where codigo_aeronave = ?";
		
		Connection cnn = FactoryConexao.getConnection();
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setLong(1, poltrona.getCodigoAeronave());
		
		ps.execute();
		
		ps.close();
		cnn.close();
		return true;
	}
	
	public Poltrona consultar(Poltrona poltrona)throws SQLException{
    	String sql = "SELECT * FROM poltrona where codigo = ?";
    	
    	Connection cnn = FactoryConexao.getConnection();
    	PreparedStatement ps = cnn.prepareStatement(sql);
    	ps.setLong(1, poltrona.getId());
    	ResultSet rs = ps.executeQuery();
    	Poltrona retorno;
    	if(rs.next()) {
    		retorno = new Poltrona (rs.getLong("codigo"),
    				rs.getString("descricao"),
    				rs.getDouble("valor"),
    				rs.getString("classe"),
    				rs.getString("detalhes"),
    				new Aeronave(rs.getLong("codigo_aeronave")));
    	}
    	
    	return poltrona;
    }
    
    public List<Poltrona> listar(Poltrona poltrona)throws SQLException{
    	String sql = "SELECT * FROM poltrona where codigo_aeronave = ?";
    	
    	Connection cnn = FactoryConexao.getConnection();
    	PreparedStatement ps = cnn.prepareStatement(sql);
    	ps.setLong(1, poltrona.getCodigoAeronave());
    	
    	ResultSet rs = ps.executeQuery();
    	List<Poltrona> poltronas = new ArrayList<Poltrona>();
    	while(rs.next()) {
    		poltronas.add(new Poltrona (rs.getLong("codigo"),
    				rs.getString("descricao"),
    				rs.getDouble("valor"),
    				rs.getString("classe"),
    				rs.getString("detalhes"),
    				new Aeronave(poltrona.getCodigoAeronave())));
    	}
    	return poltronas;
    }
}
