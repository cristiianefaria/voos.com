package br.com.voo.bll;

import java.util.List;

import br.com.voo.dal.ClienteDAO;
import br.com.voo.model.Cliente;
import br.com.voo.model.Pessoa;
import br.com.voo.util.ValidarPessoa;

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
			
			return dao.inserir(_cliente);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private void ValidarCliente(Cliente _cliente) throws Exception {
		
		if(_cliente.getPercentDesconto()> 100)
			throw new Exception("Informe um percentual de desconto v�lido!");
		
	}

	private void ValidarPessoa(Pessoa pessoa) throws Exception {

		ValidarPessoa validacao = new ValidarPessoa(pessoa);

		if (!validacao.validarPessoa().isEmpty())
			throw new Exception("Erros encontrados " + validacao.validarPessoa());

	}

	public List<Cliente> listar(String nome) throws Exception {
		return dao.listar(nome);
	}

	public boolean excluir(Long codigo) throws Exception {
		return dao.exluir(codigo);
	}
	public Cliente consultar(Long id) throws Exception{
		return dao.consultar(id);
	}

}
