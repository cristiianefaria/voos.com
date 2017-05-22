package br.com.voo.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.voo.model.Itinerario;
import br.com.voo.util.FactoryConexao;

public class ItinerarioDAO {
	
	private Connection connection;
	
	public ItinerarioDAO(){
		connection = FactoryConexao.getConnection();
	}
	
	public boolean incluir(Itinerario objeto)throws SQLException{
		
		PreparedStatement ps = connection.prepareStatement("insert into itinerario (origem, destino)"
				+ "values (?,?)");
		ps.setString(1, objeto.getOrigem());
		ps.setString(2, objeto.getDestino());
		ps.execute();
		
		ps.close();
		
		return true;
	}

	public boolean alterar(Itinerario itinerario) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("update itinerario set origem=?, destino=?"
				+ " where codigo=?");
		ps.setString(1, itinerario.getOrigem());
		ps.setString(2, itinerario.getDestino());
		ps.setLong(3, itinerario.getId());
		ps.executeUpdate();
		
		ps.close();
		
		return true;
	}
	
	public boolean remover(Long codigo) throws SQLException  {
		PreparedStatement ps = connection.prepareStatement("delete from itinerario where codigo=?");
		ps.setLong(1, codigo);
		ps.executeQuery();
		
		ps.close();
		
		return true;
	}
	
	public List<Itinerario> listar() throws SQLException {
		List<Itinerario> lista = new ArrayList<Itinerario>();
		
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("select * from itinerario");
		
		while (rs.next()){
			Itinerario itinerario = new Itinerario();
			itinerario.setId(rs.getLong("codigo"));
			itinerario.setOrigem(rs.getString("origem"));
			itinerario.setDestino(rs.getString("destino"));
			lista.add(itinerario);		
		}
		
		st.close();
		return lista;
	}
	
	public Itinerario consultar(Long codigo) throws SQLException{
		Itinerario itinerario = new Itinerario();
		PreparedStatement ps= connection.prepareStatement("select * from itinerario where codigo=?");
		ps.setLong(1, codigo);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()){
			itinerario.setId(rs.getLong("codigo"));
			itinerario.setOrigem(rs.getString("origem"));
			itinerario.setDestino(rs.getString("destino"));
		}
		
		ps.close();
		return itinerario;
	}

}
