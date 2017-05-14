package br.com.voo.model;

import java.util.Date;

public class Pessoa extends Entidade {

	
	private String nome;
	private Cpf cpf;
	private Cnpj cnpj;
	private String endereco;
	private Date dataNascimento;
	private EstadoCivil estadoCivil;
	private boolean isPessoaFisisca;
	
	public Pessoa() {
		super();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Cpf getCpf() {
		return cpf;
	}

	public void setCpf(Cpf cpf) {
		this.cpf = cpf;
	}

	public Cnpj getCnpj() {
		return cnpj;
	}

	public void setCnpj(Cnpj cnpj) {
		this.cnpj = cnpj;
	}

	public boolean isPessoaFisisca() {
		return isPessoaFisisca;
	}

	public void setPessoaFisisca(boolean isPessoaFisisca) {
		this.isPessoaFisisca = isPessoaFisisca;
	}

	
}
