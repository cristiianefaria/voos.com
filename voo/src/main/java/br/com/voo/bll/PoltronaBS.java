package br.com.voo.bll;

import java.util.logging.Logger;

import br.com.voo.dal.PoltronaDAO;
import br.com.voo.model.Aeronave;
import br.com.voo.model.Poltrona;

public class PoltronaBS {
	
	final static Logger log = Logger.getLogger(PoltronaBS.class.getName());

	PoltronaDAO dao;

	public PoltronaBS(PoltronaDAO dao) {
		this.dao = dao;
	}

	public PoltronaBS() {
		dao = new PoltronaDAO();
	}

	public boolean salvar(Poltrona poltrona) throws Exception{
			boolean retorno = false;
			
			if(poltrona != null) {
				if(poltrona.getId() == 0) {
					retorno = dao.salvar(poltrona);
				}else {
					retorno = dao.alterar(poltrona);
				}
			}else {
				throw new Exception("Poltrona enviada para ser salva está null");
			}
			
			return retorno;
	}
	
	public boolean cadastrarPoltronasNaAeronave(Poltrona poltrona,int quantidade) throws Exception{
		
		for(int i = 0; i < quantidade; i++){
			salvar(poltrona);
		}
		return true;
	}

	public boolean excluir(Poltrona poltrona)throws Exception{
		boolean retorno = false;
		if(poltrona != null) {
			if(poltrona.getId() != 0) {
				retorno = dao.excluir(poltrona);
			}else {
				log.warning("Poltrona enviada para exclusão não tem id");
				throw new Exception("Não é possivel excluir uma poltrona sem ID");
			}
		}else {
			log.warning("Poltrona enviada para exclusão esta null");
			throw new Exception("Poltrona enviada para exclusão esta null");
		}
		return retorno;
	}
	
}
