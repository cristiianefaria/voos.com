package br.com.voo.bll;

import java.sql.SQLException;

import br.com.voo.dal.AeronaveDAO;
import br.com.voo.model.Aeronave;

public class AeronaveBS {

	AeronaveDAO dao;

	public AeronaveBS(AeronaveDAO dao) {
		this.dao = dao;
	}

	public AeronaveBS() {

	}

	public boolean salvar(Aeronave aeronave) throws SQLException {
		boolean retorno = false;
		if(aeronave != null) {
			if(aeronave.getId() != 0) {
				retorno = dao.incluir(aeronave);
			}else {
				retorno = dao.alterar(aeronave);
			}
		}
		return retorno;
	}
	

	
}
