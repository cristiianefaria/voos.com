package br.com.voo.model;

public class Passageiro extends Entidade {

	Pessoa pessoa;
	private Passageiro responsavel;
	
	public Passageiro(Pessoa pessoa) {
		super();
		this.pessoa = pessoa;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}


	public Passageiro getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Passageiro responsavel) {
		this.responsavel = responsavel;
	}

	
	
}
