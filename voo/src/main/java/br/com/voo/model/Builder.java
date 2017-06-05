package br.com.voo.model;

public class Builder extends Entidade{

	
	private Pessoa pessoa;
	private int milhagem;
	private String senha;
	private Double percentDesconto;
	private TipoCliente tipoCliente;
	private boolean removido;
	private Contato contato;
	
	public Builder(){
		this.removido = false;
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
	public void setTipoCliente(String tipoCliente) {
		
		this.tipoCliente = tipoCliente == "Cliente" ? TipoCliente.clienteFinal : TipoCliente.Parceiro; 
		
	}
	public boolean isRemovido() {
		return removido;
	}
	public void setRemovido(boolean removido) {
		this.removido = removido;
	}
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	public Cliente buildCliente(){
		return new Cliente(this);
	}
	public Passageiro buildPassageiro(){
		return new Passageiro(this);
				
	}
	
	
	
}
