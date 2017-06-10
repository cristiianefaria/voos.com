package br.com.voo.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.voo.dal.VooDAO;
import br.com.voo.model.Voo;

public class VooBS {

	VooDAO dao;

	public VooBS(VooDAO dao) {
		super();
		this.dao = dao;
	}

	public VooBS() {
		super();
		dao = new VooDAO();
	}

	public boolean salvar(Voo voo) throws SQLException {
		boolean retorno = false;
			if (voo != null) {
				if (voo.getId() == 0) {
					retorno = dao.incluir(voo);
				} else {
					retorno = dao.alterar(voo);
				}
			}
			
		return retorno;
	}

	public List<Voo> listar() throws SQLException {
			return dao.listar();
	}

	public boolean remover(Voo voo) throws SQLException {
		if ((voo.getId() != null) && (voo.getId() != 0)){ 
			voo.setRemovida(true);
			dao.remover(voo);
		}
			
		return true;
	}

	public Voo consultar(Voo voo) {
		try {
			return dao.consultar(voo);
		} catch (Exception e) {
			e.printStackTrace();
			return new Voo();
		}
			
	}
}
