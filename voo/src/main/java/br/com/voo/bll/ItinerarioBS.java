package br.com.voo.bll;

import java.util.ArrayList;

import br.com.voo.dal.ItinerarioDAO;
import br.com.voo.model.Itinerario;

public class ItinerarioBS {

	ItinerarioDAO dao;

	public ItinerarioBS(ItinerarioDAO dao) {
		super();
		this.dao = dao;
	}

	public ItinerarioBS() {
		super();
	}

	public boolean salvar(Itinerario itinerario) {
		int codigo = 0;
		if (codigo != 0) {
			return dao.incluir(itinerario);
		} else {
			return dao.alterar(itinerario);
		}
	}
	
	public boolean deletar(Itinerario itinerario){
		return dao.remover(itinerario);
	}
	
	public ArrayList<Itinerario> listar(Itinerario itinerario){
		return new ArrayList<>();
	}

}
