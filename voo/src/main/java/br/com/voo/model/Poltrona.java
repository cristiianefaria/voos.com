package br.com.voo.model;

public class Poltrona extends Entidade {
	
	private String descricao;
	private Double valor;
	private String classe;
	private String detalhes;
	

	public Poltrona() {
		super();
	}

	public Poltrona(String descricao, Double valor, String classe, String detalhes) {
		this();
		this.descricao = descricao;
		this.valor = ifValorMenorQueZero(valor);
		this.classe = classe;
		this.detalhes = detalhes;
	}
	
	public Poltrona(Long id,String descricao, Double valor, String classe, String detalhes) {
		this(descricao,valor,classe,detalhes);
		this.id = ifValorMenorQueZero(id);
	}
	
	public Poltrona(Poltrona poltrona) {
		this.id = new Long(poltrona.id);
		this.valor = new Double(poltrona.valor);
		this.descricao = poltrona.descricao;
		this.classe = poltrona.classe;
		this.detalhes = poltrona.detalhes;
	}
	
	
	private Double ifValorMenorQueZero(Double valor) {
		if(valor != null) {
			if(valor > 0) {
				return new Double(valor);
				
			}
		}
		return new Double(0);
	}
	
	private Long ifValorMenorQueZero(Long valor) {
		if(valor != null) {
			if(valor > 0) {
				return new Long(valor);
			}
		}
		return new Long(0);
	}
	
	
	

	
}
