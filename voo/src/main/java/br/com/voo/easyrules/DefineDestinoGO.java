package br.com.voo.easyrules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

@Rule(name = "Destino Caldas Novas", description = "regra criada para indicar"
		+ " destino para Goias - Caldas Novas, ambiente parque aquatico")
public class DefineDestinoGO {
	
	private Indicacao indicacao;

	@Condition
	public boolean when() {
		if (indicacao.getAmbiente() == indicacao.getAmbiente().PARQUEAQUATICO
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
		System.out.println("CALDAS NOVAS");
		indicacao.setDestino(Indicacao.Destino.CALDASNOVAS);
	}

	public DefineDestinoGO(Indicacao indicacao) {
		this.indicacao = indicacao;
	}
}
