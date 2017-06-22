package br.com.voo.easyrules;


import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.easyrules.annotation.Action;

@Rule(name = "Destino Aquiris", description = "regra criada para indicar"
		+ " destino para FORTALEZA - Aquiris, ambiente parque aquatico")
public class DefineDestinoCE {

	private Indicacao indicacao;

	@Condition
	public boolean when() {
		if (indicacao.getAmbiente() == indicacao.getAmbiente().PARQUEAQUATICO
				&& (indicacao.getDinheiro() == indicacao.getDinheiro().ALTO
						|| indicacao.getDinheiro() == indicacao.getDinheiro().MODERADO)
				&& (indicacao.getCompanhia() == indicacao.getCompanhia().AMIGOS
						|| indicacao.getCompanhia() == indicacao.getCompanhia().FAMILIA)) {
			return true;
		}
		return false;
	}

	@Action (order = 1)
	public void primeiraAcao() throws Exception {
		System.out.println("AQUIRIS");
		indicacao.setDestino(Indicacao.Destino.AQUIRIS);
	}

	public DefineDestinoCE(Indicacao indicacao) {
		this.indicacao = indicacao;
	}
}
