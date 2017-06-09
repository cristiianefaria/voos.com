package br.com.voo.model;

import java.time.LocalDateTime;

public class Voo extends Entidade {
	
	private LocalDateTime horario;
	private Itinerario itinerario;
	private Aeronave aeronave;
	
	public Voo(){
		
	}
	
	public Voo(Long id){
		super(new Long(id));
	}
	
	public Voo(LocalDateTime horario, Itinerario itinerario, Aeronave aeronave) {
		super();
		this.horario = horario;
		this.itinerario = itinerario;
		this.aeronave = new Aeronave(aeronave);
	}
	
	public Voo(Long id,LocalDateTime horario, Itinerario itinerario, Aeronave aeronave) {
		super(id);
		this.horario = horario;
		this.itinerario = itinerario;
		this.aeronave = new Aeronave(aeronave);
	}

	public LocalDateTime getHorario() {
		return horario;
	}

	public long getCodigoAeronave() {
		return aeronave.getId();
	}
	
	public long getCodigoItinerario(){
		return itinerario.getId();
	}
	
	public String getOrigem(){
		return itinerario.getOrigem();
	}
	
	public String getDestino(){
		return itinerario.getDestino();
	}
	
	public String getDescricaoAeronave(){
		return aeronave.getDescricao();
	}

	public Itinerario getItinerario() {
		return itinerario;
	}

	public Aeronave getAeronave() {
		return aeronave;
	}
	
	
}
