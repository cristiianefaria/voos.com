package br.com.voo.model;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
public class Voo extends Entidade {
	
	private LocalDateTime horario;
	
	@JsonIgnore
	private Itinerario itinerario;
	
	@JsonIgnore
	private Aeronave aeronave;
	
	@JsonIgnore
	private String descricaoVoo;
	
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
	public Voo(Voo voo){
		super(voo.getId());
		this.horario = voo.getHorario();
		this.itinerario = voo.getItinerario();
		this.aeronave = voo.getAeronave();
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
	
	@JsonIgnore
	public long getCodigoAeronave() {
		return aeronave.getId();
	}
	
	@JsonIgnore
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

	@JsonIgnore
	public Aeronave getAeronave() {
		return aeronave;
	}
	
	@JsonIgnore
	public String getDescricaoVoo(){
		
		this.descricaoVoo =  " Horario: "+horario+
				             " Aeronave: "+aeronave.getDescricao()+
			               	 " Origem: " + itinerario.getOrigem()+
				             " Destino: "+ itinerario.getDestino();
		return descricaoVoo;
	}
	
	
}
