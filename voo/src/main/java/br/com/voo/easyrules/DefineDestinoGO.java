package br.com.voo.easyrules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

@Rule(name = "Destino Caldas Novas", description = "regra criada para indicar"
		+ " destino para Goias - Caldas Novas, ambiente parque aquatico")
public class DefineDestinoGO {

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

	@Action(order = 1)
	public void primeiraAcao() throws Exception {
		System.out.println("Destino para Goias - Caldas Novas");
		ambiente.setDestino(TipoDeLugar.Destino.CALDASNOVAS);
	}

	public DefineDestinoGO(Indicacao indicacao, TipoDeLugar ambiente) {
		super();
		this.indicacao = indicacao;
		this.ambiente = ambiente;
	}

}
