package br.com.voo.model;

import java.time.LocalDate;
import java.util.Date;

public class Voo extends Entidade {
	
	private LocalDate horario;
	private Itinerario itinerario;
	private Aeronave aeronave;
	
	public Voo(){
		
	}
	
	public Voo(Long id){
		super(new Long(id));
	}
	
	public Voo(LocalDate horario, Itinerario itinerario, Aeronave aeronave) {
		super();
		this.horario = horario;
		this.itinerario = itinerario;
		this.aeronave = aeronave;
	}
	
	public Voo(Long id,LocalDate horario, Itinerario itinerario, Aeronave aeronave) {
		super(id);
		this.horario = horario;
		this.itinerario = itinerario;
		this.aeronave = aeronave;
	}

	public LocalDate getHorario() {
		return horario;
	}

	public Itinerario getItinerario() {
		return itinerario;
	}

	public Aeronave getAeronave() {
		return aeronave;
	}

	public long getCodigoAeronave() {
		return aeronave.getId();
	}
	
	public long getCodigoItinerario(){
		return itinerario.getId();
	}
	
	
	
	

}
