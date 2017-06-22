package br.com.voo.easyrules;

public class Indicacao {
	
	private Destino destino;
	private Dinheiro dinheiro;
	private Companhia companhia;
	private Ambiente ambiente;
	
	public enum Destino{
		 AQUIRIS(1), CALDASNOVAS(2), FERNANDODENORONHA(3), BUZIOS(4), PENHA(5), VINHEDO(6);
		
		private final int valor;
		
		Destino(int valorOpcao){
			valor = valorOpcao;
		}
		public int getValor(){
			return valor;
		}
	}
	
	public enum Dinheiro{
		 ALTO(1), MODERADO(2), BAIXO(3);
		
		private final int valor;
		
		Dinheiro(int valorOpcao){
			valor = valorOpcao;
		}
		public int getValor(){
			return valor;
		}
	}
	
	public enum Companhia{
		 FAMILIA(1), CASAL(2), AMIGOS(3);
		
		private final int valor;
		
		Companhia(int valorOpcao){
			valor = valorOpcao;
		}
		public int getValor(){
			return valor;
		}
	}
	public enum Ambiente{
		 PARQUE(1), PRAIA(2), PARQUEAQUATICO(3);
		
		private final int valor;
		
		Ambiente(int valorOpcao){
			valor = valorOpcao;
		}
		public int getValor(){
			return valor;
		}
	}

	public Indicacao(Ambiente praia) {
		// TODO Auto-generated constructor stub
	}

	public Destino getDestino() {
		return destino;
	}

	public void setDestino(Destino destino) {
		this.destino = destino;
	}

	public Dinheiro getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(Dinheiro dinheiro) {
		this.dinheiro = dinheiro;
	}

	public Companhia getCompanhia() {
		return companhia;
	}

	public void setCompanhia(Companhia companhia) {
		this.companhia = companhia;
	}

	public Ambiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
	}

}
