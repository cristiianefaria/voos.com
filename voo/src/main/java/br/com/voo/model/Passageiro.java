package br.com.voo.model;

public class Passageiro extends Entidade {

	Pessoa pessoa;
	private boolean removido;
	private Contato contato;
	
	private Passageiro responsavel;
	
	public Passageiro(Pessoa pessoa) {
		super();
		this.pessoa = pessoa;
	}
	public Passageiro() {
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Passageiro getResponsavel() {
		return responsavel;
	}
	
	public void setResponsavel(Passageiro responsavel) {
		this.responsavel = responsavel;
	}
	public boolean isRemovido() {
		return removido;
	}
	public void setRemovido(boolean cancelado) {
		this.removido = cancelado;
	}
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}


	
	
}
