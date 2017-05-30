package br.com.voo.model;

public class Poltrona extends Entidade {
	
	private String descricao;
	private Double valor = new Double(0);
	private String classe;
	private String detalhes;
	private Aeronave aeronave;
	
	public Poltrona() {
		super();
	}
	
	public Poltrona(Long id) {
		super(id);
	}
	
	public Poltrona(Aeronave aeronave) {
		this();
		this.aeronave = new Aeronave(aeronave);
	}
	
	public Poltrona(Long id,String descricao, Double valor, String classe, String detalhes, Aeronave aeronave) {
		this(id);
		this.descricao = descricao;
		this.valor = ifValorMenorQueZero(valor);
		this.classe = classe;
		this.detalhes = detalhes;
		this.aeronave = new Aeronave(aeronave);
	}

	public Poltrona(String descricao, Double valor, String classe, String detalhes, Aeronave aeronave) {
		this();
		this.descricao = descricao;
		this.valor = ifValorMenorQueZero(valor);
		this.classe = classe;
		this.detalhes = detalhes;
		this.aeronave = new Aeronave(aeronave);
	}
	
	public Poltrona(Poltrona poltrona) {
		this.id = new Long(poltrona.id);
		this.valor = new Double(poltrona.valor);
		this.descricao = poltrona.descricao;
		this.classe = poltrona.classe;
		this.detalhes = poltrona.detalhes;
		this.aeronave = poltrona.aeronave;
	}
	
	public Poltrona(Poltrona poltrona,Aeronave aeronave) {
		this.id = new Long(poltrona.id);
		this.valor = new Double(poltrona.valor);
		this.descricao = poltrona.descricao;
		this.classe = poltrona.classe;
		this.detalhes = poltrona.detalhes;
		this.aeronave = aeronave;
	}
	
	
	private Double ifValorMenorQueZero(Double valor) {
		if(valor != null) {
			if(valor > 0) {
				return new Double(valor);
			}
		}
		return new Double(0);
	}
	
	public String getDescricao() {
		return descricao;
	}

	public Double getValor() {
		return new Double(this.valor);
	}

	public String getClasse() {
		return classe;
	}

	public String getDetalhes() {
		return detalhes;
	}
	
	public Long getCodigoAeronave() {
		return this.aeronave.getId();
	}

	
}
