package br.com.voo.model;

import java.util.Date;

public class Pessoa extends Entidade {

	private String nome;
	private Cpf cpf;
	private Cnpj cnpj;
	private String endereco;
	private Date dataNascimento;
	private EstadoCivil estadoCivil;
	private boolean removido;
	private String telefone;
	private String email;

	public Pessoa(String nome, String cpf, String cnpj, String endereco, Date dataNascimento, EstadoCivil estadoCivil,
			          String telefone, String email) {
		super();
		this.nome = nome;
		this.cpf = new Cpf(cpf);
		this.cnpj = new Cnpj(cnpj);
		this.endereco = endereco;
		this.dataNascimento = dataNascimento;
		this.estadoCivil = estadoCivil;
		this.removido = false;
		this.telefone = telefone;
		this.email = email;
	}

	public Pessoa(long id, String nome, String cpf, String cnpj, String endereco, Date dataNascimento,
			EstadoCivil estadoCivil, String telefone, String email) {
		super(id);
		this.nome = nome;
		this.cpf = new Cpf(cpf);
		this.cnpj = new Cnpj(cnpj);
		this.endereco = endereco;
		this.dataNascimento = dataNascimento;
		this.estadoCivil = estadoCivil;
		this.removido = false;
		this.telefone = telefone;
		this.email = email;
	}
	public Pessoa(Pessoa pessoa) {
		super(pessoa.id);
		this.nome = pessoa.nome;
		this.cpf = new Cpf(pessoa.cpf.numero);
		this.cnpj = new Cnpj(pessoa.cnpj.numero);
		this.endereco = pessoa.endereco;
		this.dataNascimento = pessoa.dataNascimento;
		this.estadoCivil = pessoa.estadoCivil;
		this.removido = pessoa.removido;
		this.telefone = pessoa.telefone;
		this.email = pessoa.email;
	}

	public Pessoa() {
		super();
		this.cpf = new Cpf();
		this.cnpj = new Cnpj();
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public Cpf getCpf() {
		return cpf;
	}

	public Cnpj getCnpj() {
		return cnpj;
	}

	public boolean isRemovido() {
		return removido;
	}

	public void setRemovido(boolean removido) {
		this.removido = removido;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

}
