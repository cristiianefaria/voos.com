package br.com.voo.bll;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import br.com.voo.dal.ClienteDAO;
import br.com.voo.model.Cliente;
import br.com.voo.model.Pessoa;
import br.com.voo.util.*;

public class ClienteBS {

	ClienteDAO dao;

	public ClienteBS(ClienteDAO dao) {
		super();
		this.dao = dao;
	}

	public ClienteBS() {
		super();
	}
	
	public boolean salvar(Cliente _cliente) throws Exception {
		try {
			ValidarPessoa(_cliente.getPessoa());
			ValidarCliente(_cliente);
			return dao.salvar(_cliente);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private void ValidarCliente(Cliente _cliente) throws Exception {
		
		if(_cliente.getPercentDesconto()> 100)
			throw new Exception("Informe um percentual de desconto válido!");
		
	}

	private void ValidarPessoa(Pessoa pessoa) throws Exception {

		ValidarPessoa validacao = new ValidarPessoa();

		if (!validacao.validarPeesoa(pessoa).isEmpty())
			throw new Exception("Erros encontrados " + validacao.validarPeesoa(pessoa));

	}

	public List<Cliente> buscar() {
		return dao.buscar();
	}

	public boolean excluir(int codigo) {
		return dao.exluir(codigo);
	}

}
