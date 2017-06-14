package br.com.voo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class Entidade {
	
	protected Long id;
	protected boolean entidadeRemovida;
	
	public Entidade() {
		this.id = new Long(0);
		entidadeRemovida = false;
	}
	
	public Entidade(Long id) {
		this();
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
	
	public void setRemovida(boolean removida){
		this.entidadeRemovida = removida;
	}
	
	public boolean getRemovida(){
		return this.entidadeRemovida;
	}
	
	
	
	
	
}
