package br.com.voo.bll;

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

	public void inserirPassagem(Passagem passagem) {

		try {
			if (passagem.getId() == 0) {
				dao.incluirPassagem(passagem);
			}
			else{
				dao.alterarPassagem(passagem);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void isnserirPassagens(Voo voo){
		
		for (Poltrona poltrona : voo.getAeronave().getPoltronas()) {
			
			Double valorPassagem =  voo.getItinerario().getValor()+ poltrona.getValor();
			Passagem passagem  = new Passagem(new Passageiro(),
					                  new Passageiro(),"","",false,valorPassagem,voo);
			inserirPassagem(passagem);
			
		}
		
	}
}
