package br.com.voo.model;

public abstract class Documento {

	protected String numero;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public abstract void Validar(String numero);
}
