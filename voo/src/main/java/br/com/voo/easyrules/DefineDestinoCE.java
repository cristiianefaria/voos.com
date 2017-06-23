package br.com.voo.easyrules;


import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.easyrules.annotation.Action;

@Rule(name = "Destino Aquiris", description = "regra criada para indicar"
		+ " destino para FORTALEZA - Aquiris, ambiente parque aquatico")
public class DefineDestinoCE {

	private Indicacao indicacao;
	private TipoDeLugar ambiente;

	@Condition
	public boolean when() {
		if (indicacao.getAmbiente() == indicacao.getAmbiente().PARQUEAQUATICO
						&& indicacao.getCompanhia() == indicacao.getCompanhia().AMIGOS) {
			return true;
		}
		return false;
	}

	@Action (order = 1)
	public void primeiraAcao() throws Exception {
		System.out.println("Destino para FORTALEZA - Aquiris");
		ambiente.setDestino(TipoDeLugar.Destino.AQUIRIS);
	}
	
	@Action (order = 2)
	public void segundaAcao() throws Exception {
		System.out.println("Destino para Goias - CALDAS NOVAS");
		ambiente.setDestino(TipoDeLugar.Destino.CALDASNOVAS);
	}

	public DefineDestinoCE(Indicacao indicacao, TipoDeLugar ambiente) {
		super();
		this.indicacao = indicacao;
		this.ambiente = ambiente;
	}
	
}
