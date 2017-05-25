package br.com.voo.model;

public class Itinerario extends Entidade{
	
	
	private String origem;
	private String destino;
	
	
	
	public Itinerario() {
		super();
	}
	
	public Itinerario(Long id) {
		super(id);
	}
	
	
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
}
