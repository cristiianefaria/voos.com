package br.com.voo.easyrules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;


@Rule(name = "Destino Buzios", description = "regra criada para indicar"
		+ " destino para Rio de Janeiro - Buzios, ambiente praia")
public class DefineDestinoRJ {

	private Indicacao indicacao;
	private TipoDeLugar ambiente;

	@Condition
	public boolean when() {
		if (indicacao.getAmbiente() == indicacao.getAmbiente().PRAIA
				&& indicacao.getCompanhia() == indicacao.getCompanhia().FAMILIA) {
			return true;
		}
		return false;
	}

	@Action(order = 1)
	public void primeiraAcao() throws Exception {
		System.out.println("Destino para Rio de Janeiro - Buzios");
		ambiente.setDestino(TipoDeLugar.Destino.BUZIOS);
	}

	public DefineDestinoRJ(Indicacao indicacao, TipoDeLugar ambiente) {
		super();
		this.indicacao = indicacao;
		this.ambiente = ambiente;
	}

}
