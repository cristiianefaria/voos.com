package br.com.voo.easyrules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

@Rule(name = "Destino SÃO PAULO", description = "regra criada para indicar"
		+ " destino para São Paulo - Vinhedo hopi hari, ambiente parque")
public class DefineDestinoSP {
	
	private Indicacao indicacao;

	@Condition
	public boolean when() {
		if (indicacao.getAmbiente() == indicacao.getAmbiente().PARQUE
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
		System.out.println("vinhedo");
		indicacao.setDestino(Indicacao.Destino.VINHEDO);
	}

	public DefineDestinoSP(Indicacao indicacao) {
		this.indicacao = indicacao;
	}
}
