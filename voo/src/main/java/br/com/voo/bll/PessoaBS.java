package br.com.voo.bll;

import java.util.Date;

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
		
		boolean retorno = false;
		
		if("".equals(pessoa.getNome()))
		   return retorno;
		if(pessoa.getEndereco().isEmpty())
		   return false;
		return dao.inserir(pessoa);
		
	}
	
	

	
	
	
}
