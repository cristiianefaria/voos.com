package br.com.voo.easyrules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

@Rule(name = "Destino PENHA", description = "regra criada para indicar"
		+ " destino para Santa Catarina - Penha, ambiente parque - Beto Carrero")
public class DefineDestinoSC {

	private Indicacao indicacao;
	private TipoDeLugar ambiente;

	@Condition
	public boolean when() {
		if (indicacao.getAmbiente() == indicacao.getAmbiente().PARQUE
				&& indicacao.getCompanhia() == indicacao.getCompanhia().AMIGOS) {
			return true;
		}
		return false;
	}

	@Action(order = 1)
	public void primeiraAcao() throws Exception {
		System.out.println("Destino para Santa Catarina - Penha");
		ambiente.setDestino(TipoDeLugar.Destino.PENHA);
	}

	public DefineDestinoSC(Indicacao indicacao, TipoDeLugar ambiente) {
		super();
		this.indicacao = indicacao;
		this.ambiente = ambiente;
	}

}
