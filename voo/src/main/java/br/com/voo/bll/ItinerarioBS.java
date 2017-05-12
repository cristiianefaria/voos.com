package br.com.voo.bll;

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
	
	public boolean salvar(boolean b) throws Exception{
		return true;
	}

	public boolean salvar(Itinerario itinerario) {
		return  dao.incluir(itinerario);
	}
	
}
