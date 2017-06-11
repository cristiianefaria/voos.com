package br.com.voo.bll;

import java.util.ArrayList;
import java.util.List;

import br.com.voo.dal.PassagemDAO;
import br.com.voo.model.Passageiro;
import br.com.voo.model.Passagem;
import br.com.voo.model.Poltrona;
import br.com.voo.model.Voo;

public class PassagemBS {

	private PassagemDAO dao;

	public PassagemBS(PassagemDAO dao) {
		super();
		this.dao = dao;
	}

	public PassagemBS() {

		dao = new PassagemDAO();

	}

	public void AlterarPassagem(Passagem passagem){
		try {
			dao.alterarPassagem(passagem);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Passagem> listarPassagens(Voo voo) {
		
		try {
			
			return dao.listarPassagens(voo);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Passagem>();
		}
	}

	public Passagem consultaPassagem(Long codigoPassagem) {
		try {
		
			return dao.consultarPassagem(codigoPassagem);
		} catch (Exception e) {
			
			e.printStackTrace();
		return null;
			
		}
		
	}

}
