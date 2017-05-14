package br.com.voo.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import br.com.voo.model.Itinerario;

public class ItinerarioDAO {
	
	private Connection connection;
	
	public ItinerarioDAO(){
		
	}
	
	public boolean incluir(Itinerario objeto)throws Exception{
		
		PreparedStatement ps = connection.prepareStatement("insert into itinerario (origem, destino)"
				+ "values (?,?)");
		ps.setString(1, objeto.getOrigem());
		ps.setString(2, objeto.getDestino());
		
		
		return true;
	}

	public boolean alterar(Itinerario itinerario) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public boolean remover(Itinerario itinerario) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public ArrayList<Itinerario> listar(Itinerario itinerario) {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

}
