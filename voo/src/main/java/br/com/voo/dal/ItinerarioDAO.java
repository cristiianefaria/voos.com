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
		
		return true;
	}

	public boolean alterar(Itinerario itinerario) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("update itinerario (origem=?, destino=?"
				+ " where id=?)");
		ps.setString(1, itinerario.getOrigem());
		ps.setString(2, itinerario.getDestino());
		ps.setLong(3, itinerario.getId());
		ps.executeUpdate();
		
		return true;
	}
	
	public boolean remover(Long id) throws SQLException  {
		PreparedStatement ps = connection.prepareStatement("delete from itinerario where id=?");
		ps.setLong(1, id);
		ps.executeQuery();
		return true;
	}
	
	public List<Itinerario> listar() throws SQLException {
		List<Itinerario> lista = new ArrayList<Itinerario>();
		
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("select * from itinerario");
		
		while (rs.next()){
			Itinerario itinerario = new Itinerario();
			itinerario.setId(rs.getLong("id"));
			itinerario.setOrigem(rs.getString("origem"));
			itinerario.setDestino(rs.getString("destino"));
			lista.add(itinerario);		
		}
		
		return lista;
	}

}
