package br.com.voo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Compra extends Entidade {

	private Passagem passagem;
	private Cliente cliente;
	
	public Compra() {
		super();
	}
	
	
	public Compra(Passagem passagem, Cliente cliente) {
		super();
		this.passagem = passagem;
		this.cliente = cliente;
	}


	public Passagem getPassagem() {
		return passagem;
	}


	public void setPassagem(Passagem passagem) {
		this.passagem = passagem;
	}

	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
