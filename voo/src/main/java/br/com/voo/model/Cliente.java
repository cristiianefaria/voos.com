package br.com.voo.model;

public class Cliente extends Entidade{

	private Pessoa pessoa;
	private int milhagem;
	private String senha;
	private Double percentDesconto;
	private TipoCliente tipoCliente;
	private boolean removido;
	
	public Cliente() {
		
	}
	public Cliente(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public int getMilhagem() {
		return milhagem;
	}
	public void setMilhagem(int milhagem) {
		this.milhagem = milhagem;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Double getPercentDesconto() {
		return percentDesconto;
	}
	public void setPercentDesconto(Double percentDesconto) {
		this.percentDesconto = percentDesconto;
	}
	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public boolean isRemovido() {
		return removido;
	}
	public void setRemovido(boolean removido) {
		this.removido = removido;
	}
	
}
