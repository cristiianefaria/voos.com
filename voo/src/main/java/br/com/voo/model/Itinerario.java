package br.com.voo.model;

public class Itinerario extends Entidade{
	
	
	private String origem;
	private String destino;
	private Double valor;
	private boolean removido;
	
	public Itinerario() {
		super();
		this.removido = false;
	}
	
	public Itinerario(Long id) {
		super(id);
		this.removido = false;
	}
	
	public Itinerario(String origem, String destino, Double valor, boolean removido) {
		super();
		this.origem = origem;
		this.destino = destino;
		this.valor = valor;
		this.removido = false;
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public boolean isRemovido() {
		return removido;
	}

	public void setRemovido(boolean removido) {
		this.removido = removido;
	}
	
	
}
