package br.com.voo.bll;

import java.util.ArrayList;
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
		dao = new ClienteDAO();
	}
	
	public boolean salvar(Cliente _cliente){
		try {
			
			ValidarPessoa(_cliente.getPessoa());
			ValidarCliente(_cliente);
			
			return dao.inserir(_cliente);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private void ValidarCliente(Cliente _cliente) throws Exception {
		
		if(_cliente.getPercentDesconto()> 100)
			throw new Exception("Informe um percentual de desconto válido!");
		
	}

	private void ValidarPessoa(Pessoa pessoa) throws Exception {

		ValidarPessoa validacao = new ValidarPessoa(pessoa);

		if (!validacao.validarPessoa().isEmpty())
			throw new Exception("Erros encontrados " + validacao.validarPessoa());

	}

	public List<Cliente> listar(String nome){
		try {
			List<Cliente> listaCliente = dao.listar(nome);
			return listaCliente.size() > 0 ? listaCliente : new ArrayList<Cliente>(); 
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Cliente>();
		}
		
	}

	public boolean excluir(Long codigo) throws Exception {
		return dao.exluir(codigo);
	}
	public Cliente consultar(Long id){
		try {
			
			Cliente cliente = dao.consultar(id);
			return cliente != null ? cliente : new Cliente();
		} catch (Exception e) {
			
			e.printStackTrace();
			return new Cliente();
		}
		
	}

}
