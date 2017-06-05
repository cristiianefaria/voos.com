package br.com.voo.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.voo.model.Aeronave;
import br.com.voo.model.Itinerario;
import br.com.voo.model.Voo;
import br.com.voo.util.FactoryConexao;

public class VooDAO {
	
	Connection cnn;
	AeronaveDAO aeronaveDAO;
	ItinerarioDAO itinerarioDAO;
	
	public VooDAO(){
		cnn = FactoryConexao.getConnection();
		aeronaveDAO = new AeronaveDAO();
		itinerarioDAO = new ItinerarioDAO();
	}
	
	public boolean incluir(Voo voo) throws SQLException{
		String sql = "INSERT INTO voo (horario,codigo_itinerario,"
				+ "codigo_aeronave) VALUES(?,?,?)";
	
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setDate(1, Date.valueOf(voo.getHorario()));
		ps.setLong(2, voo.getCodigoItinerario());
		ps.setLong(3, voo.getCodigoAeronave());
		
		ps.execute();
		
		ps.close();
		return true;
	}
	
	public boolean alterar(Voo voo) throws SQLException{
		String sql = "UPDATE voo set horario = ?, codigo_itinerario = ?,"
				+ " codigo_aeronave = ?, removido = ? WHERE codigo = ?";
		
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setDate(1, Date.valueOf(voo.getHorario()));
		ps.setLong(2, voo.getCodigoItinerario());
		ps.setLong(3, voo.getCodigoAeronave());
		ps.setBoolean(4, voo.getRemovida());
		ps.setLong(5, voo.getId());
		
		
		ps.execute();
		
		ps.close();
		return true;
	}
	
	public List<Voo> listar() throws SQLException {
	
		String sql = "SELECT * FROM voo WHERE removido = false";
		
		PreparedStatement ps = cnn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Voo> voos = new ArrayList<Voo>();
		while(rs.next()){
			Voo voo = new Voo(rs.getLong("codigo"),
					rs.getDate("horario").toLocalDate(),
					itinerarioDAO.consultar(rs.getLong("codigo_itinerario")),
					aeronaveDAO.consultar(new Aeronave(rs.getLong("codigo_aeronave"))));
			voos.add(voo);
		}
		rs.close();
		ps.close();
		
		return voos;
	}
	
	public boolean remover(Voo voo) throws SQLException{
		String sql = "UPDATE voo set removido = ? WHERE codigo = ?";
		
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setBoolean(1, voo.getRemovida());
		ps.setLong(2, voo.getId());
		
		ps.execute();
		
		ps.close();
		return true;
	}

	public Voo consultar(Voo voo) throws SQLException {
		String sql = "SELECT * FROM voo WHERE codigo = ? and removido = false";
		
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setLong(1, voo.getId());
		
		ResultSet rs = ps.executeQuery();
		Voo retorno = new Voo();
		
		if(rs.next()){
			retorno = new Voo(rs.getLong("codigo"),
					rs.getDate("horario").toLocalDate(),
					itinerarioDAO.consultar(rs.getLong("codigo_itinerario")),
					aeronaveDAO.consultar(new Aeronave(rs.getLong("codigo_aeronave"))));
		}
		rs.close();
		ps.close();
		
		return retorno;
	}
	
}
