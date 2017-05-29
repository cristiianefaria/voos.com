package br.com.voo.bll;

import java.sql.SQLException;
import java.util.List;

import br.com.voo.dal.VendaDAO;
import br.com.voo.model.Venda;

public class VendaBS {

	VendaDAO dao;

	public VendaBS(VendaDAO dao) {
		super();
		this.dao = dao;
	}

	public VendaBS() {
		
	}

	public boolean salvar(Venda venda) throws SQLException{
		boolean retorno = false;
		if(venda != null) {
			if(venda.getId().intValue() == 0) {
				retorno = dao.incluir(venda);
			}else {
				retorno = dao.alterar(venda);
			}
		}
		return true;
	}
	
	public boolean excluir(Venda venda) throws SQLException{
		venda.setRemovida(true);
		return this.salvar(venda);
	}
	
	public Venda consultar(Venda venda)throws Exception{
		return dao.consultar(venda);
	}
	
	public List<Venda> listar() throws SQLException, Exception{
		return dao.listar();
	}
}
