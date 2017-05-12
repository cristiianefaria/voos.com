package br.com.voo.model;

public abstract class Entidade {
	
	protected Long id;
	
	public Entidade() {
		this.id = new Long(0);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
