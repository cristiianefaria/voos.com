package br.com.voo.bll;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import br.com.voo.dal.PessoaDAO;
import br.com.voo.model.EstadoCivil;
import br.com.voo.model.Pessoa;
import br.com.voo.util.ValidarPessoa;

public class PessoaBS {

	private HttpServletRequest request;
	Long idPessoa;


	public PessoaBS(HttpServletRequest request, Long idPessoa) {

		this.request = request;
		this.idPessoa = idPessoa;
	}

	public PessoaBS() {
		// TODO Auto-generated constructor stub
	}

	private String validaCampos(String parameter) {

		return parameter != null ? parameter : "";
	}



}
