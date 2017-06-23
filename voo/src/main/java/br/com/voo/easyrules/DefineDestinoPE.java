package br.com.voo.easyrules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

@Rule(name = "Destino Fernando de Noronha", description = "regra criada para indicar"
		+ " destino para Pernambuco - Fernando de noronha, ambiente praia")
public class DefineDestinoPE {

	private Indicacao indicacao;
	private TipoDeLugar ambiente;

	@Condition
	public boolean when() {
		if (indicacao.getAmbiente() == indicacao.getAmbiente().PRAIA
				&& indicacao.getCompanhia() == indicacao.getCompanhia().CASAL) {
			return true;
		}
		return false;
	}

	@Action(order = 1)
	public void primeiraAcao() throws Exception {
		System.out.println("Destino para Pernambuco - Fernando de noronha");
		ambiente.setDestino(TipoDeLugar.Destino.FERNANDODENORONHA);
	}

	public DefineDestinoPE(Indicacao indicacao, TipoDeLugar ambiente) {
		super();
		this.indicacao = indicacao;
		this.ambiente = ambiente;
	}

}
