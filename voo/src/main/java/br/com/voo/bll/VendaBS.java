package br.com.voo.bll;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.DATA_CONVERSION;

import br.com.voo.dal.VendaDAO;
import br.com.voo.model.Passagem;
import br.com.voo.model.Venda;
import br.com.voo.util.Data;

public class VendaBS {

	VendaDAO dao;
	PassagemBS bsPassagem;
	Map<String, String> erros;
	private final int idadeMinima = 12;// idade minima;

	public VendaBS(VendaDAO dao) {
		super();
		this.dao = dao;
		erros = new HashMap<>();
		bsPassagem = new PassagemBS();
	}

	public VendaBS() {

	}

	public boolean salvar(Venda venda) throws SQLException {
		boolean retorno = false;
		if (venda != null) {

			Venda vendaValida = validaVenda(venda);

			if (venda.getId().intValue() == 0) {
				retorno = dao.incluir(venda);
			} else {
				retorno = dao.alterar(venda);
			}
		}
		return true;
	}

	private Venda validaVenda(Venda venda) {

		Passagem passagem = bsPassagem.consultaPassagem(venda.getCodigoPassagem());
		Data data = new Data(passagem.getPassageiro().getPessoa().getDataNascimento());

		if (passagem.getPassageiro().getId() == 0)
			erros.put("Passageiro", "É nescessário informar um passageiro!");
		
		if (data.calcularIdade() < idadeMinima && passagem.getResponsavel().getId() == 0)
			erros.put("Responsavel", "É Nescessário informar o responsável!");
		

		return null;

	}

	public boolean excluir(Venda venda) throws SQLException {
		venda.setRemovida(true);
		return dao.remover(venda);
	}

	public Venda consultar(Venda venda) throws Exception {
		return dao.consultar(venda);
	}

	public List<Venda> listar() throws SQLException, Exception {
		return dao.listar();
	}

}
