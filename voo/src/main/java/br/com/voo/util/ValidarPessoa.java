package br.com.voo.util;

import java.util.HashMap;
import java.util.Map;

import br.com.voo.dal.PessoaDAO;
import br.com.voo.model.Pessoa;

public class ValidarPessoa {
	
	private PessoaDAO dao;
	
	public ValidarPessoa() {
        dao = new PessoaDAO();
	}

	private Map<String, String> erros;

	public Map<String, String> validarPessoa(Pessoa pessoa) throws Exception {

		erros = new HashMap<>();
		if (!pessoa.getCpf().getNumero().isEmpty()) {
			if (!pessoa.getCpf().isCpf())
				erros.put("cpf", "CPF inválido!");
			else
				if(dao.consultar(pessoa.getCpf()).getId() > 0){
					erros.put("cpfExistente", "O numero de CPF:"
				               +pessoa.getCpf().getNumero()+"já existe no sistema!");
				}
		}
		if (!pessoa.getCnpj().getNumero().isEmpty()){
			if (!pessoa.getCnpj().isCnpj())
				erros.put("cnpj", "CNPJ inválido!");
			else
				if(dao.consultar(pessoa.getCnpj()).getId() > 0){
					erros.put("cnpjExistente", "O numero de CNPJ:"
				               +pessoa.getCnpj().getNumero()+" já existe no sistema!");
				}
		}

		if ("".equals(pessoa.getNome()))
			erros.put("nome", "Informe um nome");

		if (pessoa.getDataNascimento() == null)
			erros.put("Data de Nascimento", "Data de nascimento não informada!");

		return erros;
	}
}
