package br.com.voo.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.voo.model.Aeronave;
import br.com.voo.model.Voo;
import br.com.voo.util.FactoryConexao;

public class VooDAO {
	
	VooDAO dao;
	Connection cnn;
	
	public VooDAO(){
		this.dao = new VooDAO();
		cnn = FactoryConexao.getConnection();
	}
	
	public boolean incluir(Voo voo) throws SQLException{
		PreparedStatement ps = cnn.prepareStatement("INSERT INTO voo ");
		return true;
	}
	
	public boolean alterar(Voo voo) throws SQLException{
		PreparedStatement ps = cnn.prepareStatement("INSERT INTO voo ");
		return true;
	}
	
	public List<Voo> listar() throws SQLException {
		return new ArrayList<>();
	}
	
	public boolean remover(Long id) throws SQLException {
		PreparedStatement ps = cnn.prepareStatement("delete from voo where codigo=?");
		ps.setLong(1, id);
		ps.executeQuery();
		
		ps.close();
		
		return true;
	}

	public Voo consultar(Voo voo) throws SQLException {
		return new Voo();
	}
	
}
