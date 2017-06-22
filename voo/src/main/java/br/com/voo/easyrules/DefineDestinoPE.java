package br.com.voo.easyrules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

@Rule(name = "Destino Fernando de Noronha", description = "regra criada para indicar"
		+ " destino para Pernambuco - Fernando de noronha, ambiente praia")
public class DefineDestinoPE {

	private Indicacao indicacao;

	@Condition
	public boolean when() {
		if (indicacao.getAmbiente() == indicacao.getAmbiente().PRAIA
				&& (indicacao.getDinheiro() == indicacao.getDinheiro().ALTO
				|| indicacao.getDinheiro() == indicacao.getDinheiro().MODERADO)
				&& (indicacao.getCompanhia() == indicacao.getCompanhia().AMIGOS 
				|| indicacao.getCompanhia() == indicacao.getCompanhia().CASAL)){
			return true;
		}
		return false;
	}

	@Action (order = 1)
	public void primeiraAcao() throws Exception {
		System.out.println("Fernando de noronha");
		indicacao.setDestino(Indicacao.Destino.FERNANDODENORONHA);
	}

	public DefineDestinoPE(Indicacao indicacao) {
		this.indicacao = indicacao;
	}
}
