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

		try {
			if (validarItinerario(itinerario)) {
				if (itinerario.getId() == 0) {
					return dao.incluir(itinerario);

				} else {
					return dao.alterar(itinerario);
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deletar(Itinerario itinerario) {
		if ((itinerario.getId() != null) && (itinerario.getId() != 0)) {
			return dao.remover(itinerario);
		}
		return false;
	}

	public ArrayList<Itinerario> listar(Itinerario itinerario) {
		return new ArrayList<>();
	}

	private boolean validarItinerario(Itinerario itinerario) {
		if (itinerario != null) {
			if (itinerario.getOrigem() != null && itinerario.getDestino() != null) {
				if (itinerario.getOrigem().isEmpty() || itinerario.getDestino().isEmpty()) {
					return false;
				}
			}else{
				return false;
			}
		} else {
			return false;
		}
		return true;
	}
}
