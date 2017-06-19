package br.com.voo.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Utilitaria {

	public static String getSaudacao(String usuarioLogado) {
		Calendar cal = new GregorianCalendar();
		int hora = cal.get(Calendar.HOUR_OF_DAY);
		String mensagem = "";
		if (hora >= 0 && hora < 12) {
			mensagem = "Bom dia, ";
		} else if (hora >= 12 && hora < 18) {
			mensagem = "Boa tarde, ";
		} else if (hora >= 18 && hora < 24) {
			mensagem = "Boa noite, ";
		} else {
			mensagem = "Olá, ";
		}
		return (mensagem + usuarioLogado);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
