package br.com.voo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Data extends Date {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date data;
	private int dia;
	private int mes;
	private int ano;
	

	private Data() {
       super();
	}
	public Data(Date data) {
		super();
		this.data = data;
	}
	public Data(String data, String formato) {
		super();
		pegarData(data, formato);
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
	public void pegarData(String data, String formato){
		
		
		Data d = new Data();
		if(formato.equals("yyyy-MM-dd")){
			String[] dataArray = data.split("-");
			
			d.dia = Integer.parseInt(dataArray[2]);
			d.mes = Integer.parseInt(dataArray[1]);
			d.ano = Integer.parseInt(dataArray[0]); 
		}
		this.data = d;
		
	}
	
	public Date getData(){
		
		return this.data;
	}
	
	
}
