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

	public boolean salvar(Passageiro _passageiro) {
		try {

			Long codigoPessoa = dao.consultar(_passageiro.getId()).getPessoa().getId();
			_passageiro.getPessoa().setId(codigoPessoa);
			validarPessoa(_passageiro.getPessoa());
			validarPassageiro(_passageiro);
  
			if (_passageiro.getId() == 0)
				return dao.inserir(_passageiro);
			else
				return dao.alterar(_passageiro);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	private void validarPassageiro(Passageiro passageiro) throws Exception {

		if (idade(passageiro.getPessoa().getDataNascimento()) <= idadeMaxima && passageiro.getResponsavel() == null) {
			throw new Exception("� nescess�rio informar o respons�vel!");
		}

	}

	private int idade(Date dataNascimento) {

		Data data = new Data(dataNascimento);

		return data.calcularIdade();

	}

	private void validarPessoa(Pessoa pessoa) throws Exception {

		ValidarPessoa validacao = new ValidarPessoa();

		if (!validacao.validarPessoa(pessoa).isEmpty())
			throw new Exception("Erros encontrados " + validacao.validarPessoa(pessoa));

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
}
