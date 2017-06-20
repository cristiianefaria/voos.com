package br.com.voo.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
public class Passagem extends Entidade {

	@JsonIgnore
	private Passageiro passageiro;
	
	@JsonIgnore
	private Passageiro responsavel;
	private Voo voo;
	private String situacao;
	private String statusChekIn = "Aguardando Confirmação";
	private String hashCheckIn;
	private Poltrona poltrona;
	private boolean statusCheckIn;
	private Double valor;
	private String tipoCliente;
	


	public static final String SituacaoEmberto = "Em Aberto";
	public static final String SituacaoAlocado = "Alocada";

	public Passagem(Long codigo, Passageiro passageiro, Passageiro responsavel, String situacao, String hashCheckIn,
		boolean statusCheckIn, Double valor, Voo voo, Poltrona poltrona) {
		super();
		this.passageiro = passageiro;
		this.responsavel = responsavel != null ? responsavel : new Passageiro();
		this.situacao = situacao;
		this.hashCheckIn = hashCheckIn;
		this.voo = voo;
		this.statusCheckIn = statusCheckIn;
		this.valor = new Double(valor);
		this.id = codigo;
		this.poltrona = poltrona;
	}
    
	public Passagem() {
		super();
		this.responsavel = new Passageiro();
	}
	
	@JsonIgnore
	public Passageiro getPassageiro() {
		return passageiro;
	}

	public void setPassageiro(Passageiro passageiro) {
		this.passageiro = passageiro;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getHashCheckIn() {
		return hashCheckIn;
	}

	public void setHashCheckIn(String hashCheckIn) {
		this.hashCheckIn = hashCheckIn;
	}

	public boolean isStatusCheckIn() {
		return statusCheckIn;
	}

	public void setStatusCheckIn(boolean statusCheckIn) {
		this.statusCheckIn = statusCheckIn;
	}

	public Double getValor() {
		return voo.getItinerario().getValor() + poltrona.getValor();
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@JsonIgnore
	public Passageiro getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Passageiro responsavel) {
		this.responsavel = responsavel;
	}

	public Voo getVoo() {
		return voo;
	}

	public void setVoo(Voo voo) {
		this.voo = voo;
	}

	public Poltrona getPoltrona() {
		return poltrona;
	}

	public void setPoltrona(Poltrona poltrona) {
		this.situacao = this.SituacaoAlocado;
		this.poltrona = poltrona;
	}

	public String getStatusChekIn() {
		return statusChekIn;
	}

	public void setStatusChekIn(String statusChekIn) {
		this.statusChekIn = statusChekIn;
	}
	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

}
