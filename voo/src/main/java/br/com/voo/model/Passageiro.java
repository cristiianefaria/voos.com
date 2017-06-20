package br.com.voo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Passageiro extends Entidade {

	private Pessoa pessoa;
	private boolean removido;
	private Contato contato;
	
	public Passageiro(Pessoa pessoa) {
		super();
		this.pessoa = pessoa;
		this.removido = false;
	}
	public Passageiro(BuilderPessoaCliente build) {
		super();
		this.removido = false;
		this.id = build.getId();
		this.pessoa = build.getPessoa();
		this.contato = build.getContato();
	}
	public Passageiro(){
		super();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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
