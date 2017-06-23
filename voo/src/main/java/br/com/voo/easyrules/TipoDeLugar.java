package br.com.voo.easyrules;


public class TipoDeLugar {
	
	private Destino destino;
	
	public TipoDeLugar() {
		
	}

	public enum Destino {
		AQUIRIS(1), CALDASNOVAS(2), FERNANDODENORONHA(3), BUZIOS(4), PENHA(5), VINHEDO(6);

		private final int valor;

		Destino(int valorOpcao) {
			valor = valorOpcao;
		}

		public int getValor() {
			return valor;
		}
	}
	public Destino getDestino() {
		return destino;
	}

	public void setDestino(Destino destino) {
		this.destino = destino;
	}

}
