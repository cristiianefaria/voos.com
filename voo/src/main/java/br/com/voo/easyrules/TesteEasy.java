package br.com.voo.easyrules;

public class TesteEasy {

	Indicacao indicacao;// = new Indicacao();

	public void indi() {
		if (indicacao.getAmbiente() == indicacao.getAmbiente().PARQUEAQUATICO) {
			if (indicacao.getDinheiro() == indicacao.getDinheiro().ALTO) {
				if (indicacao.getCompanhia() == indicacao.getCompanhia().AMIGOS) {
					System.out.println("A voos.com recomenda CALDAS NOVAS");
				}
			}
		} else if (indicacao.getAmbiente() == indicacao.getAmbiente().PRAIA) {
			if (indicacao.getDinheiro() == indicacao.getDinheiro().ALTO) {
				if (indicacao.getCompanhia() == indicacao.getCompanhia().CASAL) {
					System.out.println("A voos.com recomenda FERNANDO DE NORONHA");
				}
			}

		} else if (indicacao.getAmbiente() == indicacao.getAmbiente().PARQUE) {
			if (indicacao.getDinheiro() == indicacao.getDinheiro().BAIXO) {
				if ((indicacao.getCompanhia() == indicacao.getCompanhia().AMIGOS) 
						&& (indicacao.getCompanhia() == indicacao.getCompanhia().FAMILIA)) {
					System.out.println("A voos.com recomenda HOPI HARI");
				}else  {
					System.out.println("A voos.com recomenda BETO CARRERO WORLD");
				}
			}else if(indicacao.getDinheiro() == indicacao.getDinheiro().MODERADO){
				
			}else{
				
			}
		}
	}
}