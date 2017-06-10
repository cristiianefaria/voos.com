package br.com.voo.model;

public class Passagem extends Entidade {

	private Passageiro passageiro;
	private Passageiro responsavel;
	private Voo voo;
	private String situacao;
	private String statusChekIn;
	private String hashCheckIn;
	private Poltrona poltrona;
	private boolean statusCheckIn;
	private Double valor;

	public Passagem(Passageiro passageiro, Passageiro respossavel, String sitiacao, String hashCheckIn,
			boolean statusCheckIn, Double valor, Voo voo) {
		super();
		this.passageiro = passageiro;
		this.responsavel = respossavel;
		this.situacao = sitiacao;
		this.hashCheckIn = hashCheckIn;
		this.voo = voo;
		this.statusCheckIn = statusCheckIn;
		this.valor = new Double(valor);
	}

	public Passagem() {
       super();
	}

	public Passageiro getPassageiro() {
		return passageiro;
	}

	public void setPassageiro(Passageiro passageiro) {
		this.passageiro = passageiro;
	}

	public Passageiro getRespossavel() {
		return responsavel;
	}

	public void setRespossavel(Passageiro respossavel) {
		this.responsavel = respossavel;
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
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

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
		this.poltrona = poltrona;
	}

	public String getStatusChekIn() {
		return statusChekIn;
	}

	public void setStatusChekIn(String statusChekIn) {
		this.statusChekIn = statusChekIn;
	}

}
