package br.com.voo.easyrules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

@Rule(name = "Destino PENHA", description = "regra criada para indicar"
		+ " destino para Santa Catarina - Penha, ambiente parque - Beto Carrero")
public class DefineDestinoSC {
	
	private Indicacao indicacao;

	@Condition
	public boolean when() {
		if (indicacao.getAmbiente() == indicacao.getAmbiente().PARQUE
				&& (indicacao.getDinheiro() == indicacao.getDinheiro().ALTO
						|| indicacao.getDinheiro() == indicacao.getDinheiro().MODERADO)
				&& (indicacao.getCompanhia() == indicacao.getCompanhia().AMIGOS
						|| indicacao.getCompanhia() == indicacao.getCompanhia().CASAL)) {
			return true;
		}
		return false;
	}

	@Action (order = 1)
	public void primeiraAcao() throws Exception {
		System.out.println("penha");
		indicacao.setDestino(Indicacao.Destino.PENHA);
	}

	public DefineDestinoSC(Indicacao indicacao) {
		this.indicacao = indicacao;
	}
}
