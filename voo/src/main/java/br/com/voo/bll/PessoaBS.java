package br.com.voo.bll;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import br.com.voo.model.EstadoCivil;
import br.com.voo.model.Pessoa;
import br.com.voo.util.ValidarPessoa;

public class PessoaBS {

	private HttpServletRequest request;
	Long idPessoa;
	
	public PessoaBS(HttpServletRequest request,Long idPessoa){
		
		this.request = request;
		this.idPessoa = idPessoa;
	}
	
	public Pessoa obterPessoa(){
		
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf") != null ? request.getParameter("cpf") : "";
		String cnpj = request.getParameter("cnpj") != null ? request.getParameter("cnpj") : "";
		String endereco = request.getParameter("endereco");

		String data = request.getParameter("dataNascimento");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dataNacimento = format.parse(data);

			EstadoCivil estadoCivil = ValidarPessoa.estadoCivilDescricao(request.getParameter("estadoCivil"));
			String telefone = request.getParameter("telefone");
			String email = request.getParameter("email");

			return new Pessoa(idPessoa, nome, cpf, cnpj, endereco, dataNacimento, estadoCivil, telefone, email);
		
		} catch (Exception e) {
			e.printStackTrace();
            return null;
		}
	}
}
