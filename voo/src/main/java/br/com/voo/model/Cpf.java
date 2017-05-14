package br.com.voo.model;

public class Cpf {

	private String numero;
	private boolean isCpf;

	public Cpf(String numero) {
		this.numero = numero;
		ValidarCpf(numero);
	}

	public String getNumero() {
		return numero;
	}

	private void ValidarCpf(String numero) {
		if (numero.equals("00000000000") || numero.equals("11111111111") || numero.equals("22222222222")
				|| numero.equals("33333333333") || numero.equals("44444444444") || numero.equals("55555555555")
				|| numero.equals("66666666666") || numero.equals("77777777777") || numero.equals("88888888888")
				|| numero.equals("99999999999") || (numero.length() != 11)) {
			isCpf = false;
			return;
		}
			
		char dig10, dig11;
		int sm, i, r, num, peso;

			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				
				num = (int) (numero.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); 
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (numero.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			
			if ((dig10 == numero.charAt(9)) && (dig11 == numero.charAt(10)))
				isCpf = true;
			else
				isCpf = false;
		

	}

	public boolean isCpf() {
		return isCpf;

	}

}
