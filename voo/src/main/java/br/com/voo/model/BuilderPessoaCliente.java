package br.com.voo.model;

public class BuilderPessoaCliente extends Entidade {

	private Pessoa pessoa;
	private int milhagem;
	private String senha;
	private Double percentDesconto;
	private TipoCliente tipoCliente;
	private boolean removido;
	private Contato contato;

	public BuilderPessoaCliente(Pessoa pessoa) {
		this.removido = false;
		this.pessoa = new Pessoa(pessoa);
		milhagem = 0;
		percentDesconto = new Double(0);
	}
	
	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public int getMilhagem() {
		return milhagem;
	}

	public String getSenha() {
		return senha;
	}

	public Double getPercentDesconto() {
		return percentDesconto;
	}

	public boolean isRemovido() {
		return removido;
	}

	public Contato getContato() {
		return contato;
	}

	public BuilderPessoaCliente setIdBuilder(Long codigo){
		
		this.id = codigo;
		
		return this;
	}

	public BuilderPessoaCliente setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
		return this;
	}

	public BuilderPessoaCliente setMilhagem(int milhagem) {
		this.milhagem = milhagem;
		return this;
	}

	public BuilderPessoaCliente setSenha(String senha) {
		this.senha = senha;
		return this;
	}

	public BuilderPessoaCliente setPercentDesconto(Double percentDesconto) {

		this.percentDesconto = percentDesconto;
		return this;

	}

	public BuilderPessoaCliente setPercentDesconto(String percentDesconto) {

		if (percentDesconto == null || !"".equals(percentDesconto)) {
			this.percentDesconto = Double.parseDouble(percentDesconto);
		}
		return this;

	}

	public BuilderPessoaCliente setTipoCliente(String tipoCliente) {

		this.tipoCliente = tipoCliente == "Cliente" ? TipoCliente.clienteFinal : TipoCliente.Parceiro;
		return this;
	}

	public BuilderPessoaCliente setRemovido(boolean removido) {
		this.removido = removido;
		return this;
	}

	public BuilderPessoaCliente setContato(Contato contato) {
		this.contato = contato;
		return this;
	}

	public Cliente buildCliente() {
		return new Cliente(this);
	}

	public Passageiro buildPassageiro() {
		return new Passageiro(this);

	}

}
