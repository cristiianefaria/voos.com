package br.com.voo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Data extends Date {

	private Date data;
	private int dia;
	private int mes;
	private int ano;

	private Data() {

	}

	public Data(Date data) {
		this.data = data;
	}

	public int calcularIdade() {
		SimpleDateFormat sdfNascimento = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdfAtual = new SimpleDateFormat("dd-MM-yyyy");
		String nacscimento = sdfNascimento.format(data);
		String atual = sdfAtual.format(new Date());

		Data dataNascimento = separarData(nacscimento);
		Data dataAtual = separarData(atual);

		return compararDatas(dataNascimento, dataAtual);

	}

	private int compararDatas(Data dataNascimento, Data dataAtual) {

		int anos = dataAtual.ano - dataNascimento.ano;
		if (dataAtual.mes < dataNascimento.mes) {
			anos = anos - 1;
		} else if (dataAtual.mes == dataNascimento.mes) {
			if (dataAtual.dia < dataNascimento.dia) {
				anos = anos - 1;
			}
		}
		return anos;

	}

	private Data separarData(String nacscimento) {

		Data novaData = new Data();
		String[] dataArray = nacscimento.split("-");
		novaData.dia = Integer.parseInt(dataArray[0]);
		novaData.mes = Integer.parseInt(dataArray[1]);
		novaData.ano = Integer.parseInt(dataArray[2]);

		return novaData;
	}
}
