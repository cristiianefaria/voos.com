package br.com.voo.model;

public class Contato extends Entidade {
	
	private String nome;
	private String telefone1;
	private String telefone2;
	private Passageiro passageiro;
	private String relacao;
	
	public Contato(Passageiro passageiro) {
		this.passageiro = passageiro;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Passageiro getPassageiro() {
		return passageiro;
	}
	
	public String getRelacao() {
		return relacao;
	}
	public void setRelacao(String relacao) {
		this.relacao = relacao;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
	

}
