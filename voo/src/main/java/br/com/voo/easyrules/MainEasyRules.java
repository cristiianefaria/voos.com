package br.com.voo.easyrules;

import org.easyrules.api.RulesEngine;
import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

public class MainEasyRules {

	public static void main(String[] args) {

		Indicacao indicacao = new Indicacao(Indicacao.Ambiente.PRAIA);

		DefineDestinoCE defineDestinoCE = new DefineDestinoCE(indicacao);
		DefineDestinoPE defineDestinoPE = new DefineDestinoPE(indicacao);
		DefineDestinoGO defineDestinoGO = new DefineDestinoGO(indicacao);
		DefineDestinoRJ defineDestinoRJ = new DefineDestinoRJ(indicacao);
		DefineDestinoSC defineDestinoSC = new DefineDestinoSC(indicacao);
		DefineDestinoSP defineDestinoSP = new DefineDestinoSP(indicacao);

		RulesEngine rulesEngine = aNewRulesEngine().withSilentMode(true).build();

		rulesEngine.registerRule(defineDestinoCE);
		rulesEngine.registerRule(defineDestinoPE);
		rulesEngine.registerRule(defineDestinoGO);

		rulesEngine.registerRule(defineDestinoRJ);
		rulesEngine.registerRule(defineDestinoSC);
		rulesEngine.registerRule(defineDestinoSP);
		rulesEngine.fireRules();

	}

}
