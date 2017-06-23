package br.com.voo.easyrules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

@Rule(name = "Destino SÃO PAULO", description = "regra criada para indicar"
		+ " destino para São Paulo - Vinhedo hopi hari, ambiente parque")
public class DefineDestinoSP {

	private Indicacao indicacao;
	private TipoDeLugar ambiente;

	@Condition
	public boolean when() {
		if (indicacao.getAmbiente() == indicacao.getAmbiente().PARQUE
				&& indicacao.getCompanhia() == indicacao.getCompanhia().FAMILIA) {
			return true;
		}
		return false;
	}

	@Action(order = 1)
	public void primeiraAcao() throws Exception {
		System.out.println("Destino para S�o Paulo - Vinhedo hopi hari");
		ambiente.setDestino(TipoDeLugar.Destino.VINHEDO);
	}

	public DefineDestinoSP(Indicacao indicacao, TipoDeLugar ambiente) {
		super();
		this.indicacao = indicacao;
		this.ambiente = ambiente;
	}

}
