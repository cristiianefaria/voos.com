package br.com.voo.util;

import java.util.HashMap;
import java.util.Map;

import br.com.voo.model.Pessoa;

public class ValidarPessoa {
	
	private Map<String, String> erros;

	public Map<String, String> validarPeesoa(Pessoa pessoa){
		
		erros = new HashMap<>();
		if (pessoa.isPessoaFisisca()) {
			if (!pessoa.getCpf().isCpf())
				erros.put("cpf", "CPF inválido!");
		} else {
			if (!pessoa.getCnpj().isCnpj())
				erros.put("cnpj", "CNPJ inválido!");
		}

		if ("".equals(pessoa.getNome()))
			erros.put("nome", "Informe um nome");
		
		if (pessoa.getDataNascimento() == null)
			erros.put("Data de Nascimento", "Data de nascimento não informada!");
		
		return erros;
	}
}
