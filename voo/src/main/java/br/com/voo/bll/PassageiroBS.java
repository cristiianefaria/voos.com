package br.com.voo.bll;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.voo.dal.PassageiroDAO;
import br.com.voo.model.Passageiro;
import br.com.voo.model.Pessoa;
import br.com.voo.util.Data;
import br.com.voo.util.ValidarPessoa;

public class PassageiroBS {

	private PassageiroDAO dao;
	private final int idadeMaxima = 12;

	public PassageiroBS(PassageiroDAO dao) {
		this.dao = dao;
	}

	public PassageiroBS() {
		this.dao = new PassageiroDAO();
	}

	public boolean salvar(Passageiro _passageiro) throws Exception {
		
		try {
			
			//validarPessoa(_passageiro.getPessoa());
			
			if (_passageiro.getId() == 0){
				return dao.inserir(_passageiro);
			}else{
				Long codigoPessoa = dao.consultar(_passageiro.getId()).getPessoa().getId();
				_passageiro.getPessoa().setId(codigoPessoa);
				return dao.alterar(_passageiro);
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	private int idade(Date dataNascimento) {

		Data data = new Data(dataNascimento);

		return data.calcularIdade();

	}

	private void validarPessoa(Pessoa pessoa) throws Exception {

		ValidarPessoa validacao = new ValidarPessoa(pessoa);

		if (!validacao.validarPessoa().isEmpty())
			throw new Exception("Erros encontrados " + validacao.validarPessoa());

	}

	public List<Passageiro> listar(String nome) {
		try {

			return dao.listar(nome);

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Passageiro>();
		}
	}

	public boolean excluir(Long id) {
		try {
			return dao.excluir(id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public Passageiro consultar(Long id) {
		try {
			return dao.consultar(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new Passageiro();
		}
	}

	public Passageiro consultarPorCpf(String busca) throws Exception {
		
			
			Passageiro passageiro = dao.buscar(busca);
		    if(passageiro.getId() == 0)
		    	throw new Exception("Passageiro não encontrado por favor realize o cadastro!");
		    
		    return passageiro;
		 
	}

	
}
