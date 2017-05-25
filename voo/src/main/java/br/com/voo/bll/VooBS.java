package br.com.voo.bll;

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

	public boolean salvar(Voo voo) {
		boolean retorno = false;
		try {
			if (voo != null) {
				if (voo.getId() == 0) {
					retorno = dao.incluir(voo);
				} else {
					retorno = dao.alterar(voo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return retorno;
	}

	public List<Voo> listar() {
		try {
			return dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Voo>();
		}

	}

	public boolean remover(Voo voo) {
		try {
			if ((voo.getId() != null) && (voo.getId() != 0)) {
				dao.remover(voo.getId());
				return true;
		}
		} catch (Exception e) {
			e.printStackTrace();
			}
		return false;
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
