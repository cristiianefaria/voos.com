package br.com.voo.model;

public class Passageiro extends Entidade {

	private Pessoa pessoa;
	private boolean removido;
	private Contato contato;
	
	private Passageiro responsavel;
	
	public Passageiro(Pessoa pessoa) {
		super();
		this.pessoa = pessoa;
		this.removido = false;
	}
	public Passageiro(Builder build) {
		super();
		this.removido = false;
		this.pessoa = build.getPessoa();
		this.contato = build.getContato();
	}
	public Passageiro(){
		
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
	public void setRemovido(boolean removido) {
		this.removido = removido;
	}
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}


	
	
}
