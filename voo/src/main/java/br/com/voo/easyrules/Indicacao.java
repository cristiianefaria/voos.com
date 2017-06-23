package br.com.voo.easyrules;

public class Indicacao {

	private Ambiente ambiente;
	private Companhia companhia;
	
	public Indicacao(Ambiente ambiente, Companhia companhia) {
		super();
		this.ambiente = ambiente;
		this.companhia = companhia;
	}

	public enum Companhia {
		FAMILIA(1), CASAL(2), AMIGOS(3);

		private final int valor;

		Companhia(int valorOpcao) {
			valor = valorOpcao;
		}

		public int getValor() {
			return valor;
		}
	}
	public enum Ambiente {
		PARQUE(1), PRAIA(2), PARQUEAQUATICO(3);

		private final int valor;

		Ambiente(int valorOpcao) {
			valor = valorOpcao;
		}

		public int getValor() {
			return valor;
		}
	}

	public Ambiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
	}


	public Companhia getCompanhia() {
		return companhia;
	}

	public void setCompanhia(Companhia companhia) {
		this.companhia = companhia;
	}

}
