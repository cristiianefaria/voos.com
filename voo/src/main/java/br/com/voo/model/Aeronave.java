package br.com.voo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Aeronave extends Entidade {

	private String descricao;
	
	@JsonIgnore
	private List<Poltrona> poltronas;

	
	public Aeronave() {
		super();
		poltronas = new ArrayList<Poltrona>();
	}
	
	public Aeronave(Long id) {
		super(id);
		poltronas = new ArrayList<Poltrona>();
	}
	
	public Aeronave(String descricao, List<Poltrona> poltronas) {
		this();
		this.descricao = descricao;
		cadastrarPoltrona(poltronas);
	}
	
	public Aeronave(Long id,String descricao, List	<Poltrona> poltronas) {
		this(id);
		this.descricao = descricao;
		cadastrarPoltrona(poltronas);
	}
	
	public Aeronave(Aeronave aeronave){
		this.id = aeronave.id;
		this.descricao = aeronave.descricao;
		this.poltronas = new ArrayList<>(aeronave.poltronas);
	}
	
	public int getQuantidadeDePoltronas() {
		return this.poltronas.size();
	}
	
	public void cadastrarPoltrona(List<Poltrona> poltronas){
		if(poltronas != null) {
			this.poltronas.addAll(poltronas);
		}
	}
	
	public void cadastrarPoltrona(Poltrona poltrona) {
		if(poltrona != null) {
			this.poltronas.add(poltrona);
		}
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public List<Poltrona> getPoltronas(){
		return this.poltronas;
	}
	
}
