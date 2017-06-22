package br.com.voo.easyrules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

import br.com.voo.model.Itinerario;

@Rule(name = "Destino Buzios", description = "regra criada para indicar"
		+ " destino para Rio de Janeiro - Buzios, ambiente praia")
public class DefineDestinoRJ {
	
	private Indicacao indicacao;

	@Condition
	public boolean when() {
		if (indicacao.getAmbiente() == indicacao.getAmbiente().PRAIA
				&& (indicacao.getDinheiro() == indicacao.getDinheiro().BAIXO
				|| indicacao.getDinheiro() == indicacao.getDinheiro().MODERADO)
				&& (indicacao.getCompanhia() == indicacao.getCompanhia().AMIGOS 
				|| indicacao.getCompanhia() == indicacao.getCompanhia().FAMILIA)){
			return true;
		}
		return false;
	}

	@Action (order = 1)
	public void primeiraAcao() throws Exception {
		System.out.println("buzios");
		indicacao.setDestino(Indicacao.Destino.BUZIOS);
	}

	public DefineDestinoRJ(Indicacao indicacao) {
		this.indicacao = indicacao;
	}
}
