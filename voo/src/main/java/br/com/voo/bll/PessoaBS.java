package br.com.voo.bll;

import java.util.Date;
import java.util.List;

import br.com.voo.dal.PessoaDAO;
import br.com.voo.model.EstadoCivil;
import br.com.voo.model.Pessoa;

public class PessoaBS {

	PessoaDAO dao;

	public PessoaBS(PessoaDAO dao) {

		this.dao = dao;
	}

	public PessoaBS() {

	}

	public boolean inserir(Pessoa pessoa) {

		if (ValidarPessoa(pessoa))
			return dao.inserir(pessoa);

		return false;
	}

	private boolean ValidarPessoa(Pessoa pessoa) {

		if (pessoa.isPessoaFisisca()) {
			if (!pessoa.getCpf().isCpf())
				return false;
		} else {
			if (!pessoa.getCnpj().isCnpj())
				return false;
		}

		if ("".equals(pessoa.getNome()))
			return false;

		if (pessoa.getDataNascimento() == null)
			return false;
		return true;

	}

	public List<Pessoa> buscar(Pessoa pessoa) {
		return dao.buscar(pessoa);
	}

	public boolean excluir(int codigo) {

		return dao.exluir(codigo);
	}

	public boolean Alterar(Pessoa _pessoa) {

		if (ValidarPessoa(_pessoa))
			return dao.Alterar(_pessoa);

		return false;
	}
}
