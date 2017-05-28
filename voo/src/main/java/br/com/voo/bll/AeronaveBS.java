package br.com.voo.bll;

import java.sql.SQLException;
import java.util.List;

import br.com.voo.dal.AeronaveDAO;
import br.com.voo.model.Aeronave;

public class AeronaveBS {

	AeronaveDAO dao;

	public AeronaveBS(AeronaveDAO dao) {
		this.dao = dao;
	}

	public AeronaveBS() {
		dao = new AeronaveDAO();
	}

	public boolean salvar(Aeronave aeronave) throws SQLException {
		boolean retorno = false;
		if(aeronave != null) {
			if(aeronave.getId().intValue() == 0) {
				retorno = dao.incluir(aeronave);
			}else {
				retorno = dao.alterar(aeronave);
			}
		}
		return retorno;
	}

	public List<Aeronave> listar() throws SQLException {
		return dao.listar();
	}
	
	public boolean excluir(Aeronave aeronave) throws SQLException{
		aeronave.setRemovida(true);
		return this.salvar(aeronave);
	}

	public Aeronave consultar(Aeronave aeronave) throws SQLException {
		return dao.consultar(aeronave);
	}
	

	
}
