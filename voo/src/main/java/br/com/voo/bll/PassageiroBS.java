package br.com.voo.bll;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.voo.dal.PassageiroDAO;
import br.com.voo.model.Passageiro;
import br.com.voo.model.Pessoa;
import br.com.voo.util.Data;
import br.com.voo.util.ValidarPessoa;

public class PassageiroBS {

	private PassageiroDAO dao;
	private final int idadeMaxima = 12;
	
	public PassageiroBS(PassageiroDAO dao){
	  this.dao = dao;
	}
	public boolean salvar(Passageiro _passageiro) throws Exception{
		try {
			
			validarPessoa(_passageiro.getPessoa());
			validarPassageiro(_passageiro);
			
			if (_passageiro.getId() == 0)
				return dao.inserir(_passageiro);
			else
				return dao.atualiza(_passageiro);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		
	}
	private void validarPassageiro(Passageiro passageiro) throws Exception {
		
		if(idade(passageiro.getPessoa().getDataNascimento())<=idadeMaxima &&
				passageiro.getResponsavel() == null){
			throw new Exception("É nescessário informar o responsável!");
			
		}
		
	}
	private int idade(Date dataNascimento) {
      
       Data data = new Data(dataNascimento);
       
       return data.calcularIdade();
       
	}
	private void validarPessoa(Pessoa pessoa) throws Exception {
		
		ValidarPessoa validacao = new ValidarPessoa();

		if (!validacao.validarPeesoa(pessoa).isEmpty())
			throw new Exception("Erros encontrados " + validacao.validarPeesoa(pessoa));

	}
	public List<Passageiro> listar() {
		
		return dao.listar();
	}

	public boolean excluir(Passageiro passageiro) throws Exception {
		return dao.excluir(passageiro);

	}
}
