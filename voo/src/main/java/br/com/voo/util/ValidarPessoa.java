package br.com.voo.util;

import java.util.HashMap;
import java.util.Map;

import br.com.voo.dal.PessoaDAO;
import br.com.voo.model.EstadoCivil;
import br.com.voo.model.Pessoa;

public class ValidarPessoa {

	private PessoaDAO dao;

	public ValidarPessoa() {
		dao = new PessoaDAO();
	}

	private Map<String, String> erros;

	public Map<String, String> validarPessoa(Pessoa pessoa) throws Exception {

		erros = new HashMap<>();
		
		if(pessoa.getCpf().getNumero().isEmpty() && pessoa.getCnpj().getNumero().isEmpty()){
			erros.put("cpfCnpjVazio", "Um número de CPF ou CNPJ deve ser informado!");
		}
		
		if (!pessoa.getCpf().getNumero().isEmpty()) {
			if (!pessoa.getCpf().isCpf())
				erros.put("cpf", "CPF inválido!");
			else if (dao.consultar(pessoa.getCpf()).getId() > 0 && pessoa.getId() == 0) {
				erros.put("cpfExistente", "O numero de CPF:" + pessoa.getCpf().getNumero() + "já existe no sistema!");
			}
		}
		if (!pessoa.getCnpj().getNumero().isEmpty() && pessoa.getId() == 0) {
			if (!pessoa.getCnpj().isCnpj())
				erros.put("cnpj", "CNPJ inv�lido!");
			else if (dao.consultar(pessoa.getCnpj()).getId() > 0) {
				erros.put("cnpjExistente",
						"O numero de CNPJ:" + pessoa.getCnpj().getNumero() + " já existe no sistema!");
			}
		}

		if ("".equals(pessoa.getNome()))
			erros.put("nome", "Informe um nome");

		if (pessoa.getDataNascimento() == null)
			erros.put("Data de Nascimento", "Data de nascimento não informada!");

		return erros;
	}

	public static String estadoCivilDescricao(EstadoCivil estadoCivil) {

		String descricao = "";
		switch (estadoCivil) {
		case Casado:
			descricao = "Casado";
			break;
		case Solteiro:
			descricao = "Solteito";
			break;
		case Divorciado:
			descricao = "Divorciado";
			break;
		case Viuvo:
			descricao = "Viuvo";
			break;
		default:
			break;
		}

		return descricao;
	}
	public static EstadoCivil estadoCivilDescricao(String descricao) {

		EstadoCivil estadoCivil = EstadoCivil.Solteiro;
		switch (descricao) {
		case "Casado":
			estadoCivil = EstadoCivil.Casado;
			break;
		case "Divorciado":
			estadoCivil = EstadoCivil.Divorciado;
			break;
		case "Viuvo":
			estadoCivil = EstadoCivil.Viuvo;
			break;
		default:
			break;
		}

		return estadoCivil;
	}
}
