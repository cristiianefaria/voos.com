package br.com.voo.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Venda extends Entidade {

	private LocalDate horario;
	private Double desconto;
	private String tipoDePagamento;
	private String situacao;
	private Passagem passagem;
	private Cliente cliente;
	

	public Venda(){
		super();
		this.horario = LocalDate.now();
	}
	
	public Venda(Long id){
		super(id);
	}
	
	public Venda(LocalDate horario, Double desconto, String tipoDePagamento, String situacao, Passagem passagem,
			Cliente cliente) {
		super();
		this.horario = horario;
		this.desconto = desconto;
		this.tipoDePagamento = tipoDePagamento;
		this.situacao = situacao;
		this.passagem = passagem;
		this.cliente = cliente;
	}
	
	public Venda(Long id,LocalDate horario, Double desconto, String tipoDePagamento, String situacao, Passagem passagem,
			Cliente cliente) {
		super(id);
		this.horario = horario;
		this.desconto = desconto;
		this.tipoDePagamento = tipoDePagamento;
		this.situacao = situacao;
		this.passagem = passagem;
		this.cliente = cliente;
	}
	
	public LocalDate getHorario() {
		return horario;
	}
	
	public Double getDesconto() {
		return desconto;
	}
	
	public String getTipoDePagamento() {
		return tipoDePagamento;
	}
	
	public String getSituacao() {
		return situacao;
	}
	
	public Long getCodigoPassagem(){
		return this.passagem.getId();
	}
	
	public Long getCodigoCliente(){
		return this.cliente.getId();
	}

	public void setPassagem(Passagem passagem) {
		this.passagem = passagem;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
	
}