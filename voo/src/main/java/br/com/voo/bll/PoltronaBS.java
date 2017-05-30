package br.com.voo.bll;

import java.sql.SQLException;
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
		poltrona.setRemovida(true);
		return this.salvar(poltrona);
	}

	public Poltrona consultar(Poltrona poltrona) throws SQLException {
		return dao.consultar(poltrona);
	}
	
}
