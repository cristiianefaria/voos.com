package br.com.voo.bll;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

	public void AlterarPassagem(Passagem passagem) {
		try {
			dao.alterarPassagem(passagem);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Passagem> listarPassagens(Voo voo) throws Exception {
		return dao.listarPassagens(voo);
	}

	public Passagem consultaPassagem(Long codigoPassagem)throws Exception {
			return dao.consultarPassagem(codigoPassagem);
	}

	public Passagem consultaPassagemPeloHash(String hashCode) throws Exception {
		return dao.consultarPassagemPeloHashCode(hashCode);
	}

	public String obterHash() {
		
		UUID hashNumero = UUID.randomUUID();
		return hashNumero.toString();
	}

	public void confirmarCheckin(Passagem passagem) throws Exception {
		
		if(passagem != null && passagem.getId()> 0){
			
			passagem.setStatusCheckIn(true);
			passagem.setStatusChekIn("Confirmado");
			dao.alterarPassagem(passagem);
			
		}else
			throw new Exception("Passagem não encontrada! verifique o identificador.");
		
	}

}
