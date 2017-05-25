package br.com.voo.model;

import java.util.Date;

public class Voo extends Entidade {
	
	private Date data;
	private Itinerario itinerario;
	private Aeronave aeronave;
	
	public Voo(){}
	
	public Voo(Date data, Itinerario itinerario, Aeronave aeronave) {
		super();
		this.data = data;
		this.itinerario = itinerario;
		this.aeronave = aeronave;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Itinerario getItinerario() {
		return itinerario;
	}

	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}

	public Aeronave getAeronave() {
		return aeronave;
	}

	public void setAeronave(Aeronave aeronave) {
		this.aeronave = aeronave;
	}
	
}
