package br.com.voo.easyrules;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

import org.easyrules.api.RulesEngine;

public class MainEasyRules {

	public static void main(String[] args) {

		TipoDeLugar tipoDeLugar = new TipoDeLugar();
		Indicacao indicacao = new Indicacao(Indicacao.Ambiente.PARQUE, Indicacao.Companhia.AMIGOS);

		DefineDestinoCE defineDestinoCE = new DefineDestinoCE(indicacao, tipoDeLugar);
		DefineDestinoPE defineDestinoPE = new DefineDestinoPE(indicacao, tipoDeLugar);
		DefineDestinoGO defineDestinoGO = new DefineDestinoGO(indicacao, tipoDeLugar);
		DefineDestinoRJ defineDestinoRJ = new DefineDestinoRJ(indicacao, tipoDeLugar);
		DefineDestinoSC defineDestinoSC = new DefineDestinoSC(indicacao, tipoDeLugar);
		DefineDestinoSP defineDestinoSP = new DefineDestinoSP(indicacao, tipoDeLugar);
		
		

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
