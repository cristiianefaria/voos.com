package br.com.voo.model;

public abstract class Entidade {
	
	protected Long id;
	
	public Entidade() {
		this.id = new Long(0);
	}
	
	public Entidade(Long id) {
		super();
		this.id = ifValorMenorQueZero(id);
	}

	public Long getId() {
		return new Long(this.id);
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	private Long ifValorMenorQueZero(Long valor) {
		if(valor != null) {
			if(valor > 0) {
				return new Long(valor);
			}
		}
		return new Long(0);
	}
	
	
	
}
