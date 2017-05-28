package br.com.voo.bll;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.omg.CORBA._PolicyStub;

import br.com.voo.dal.ClienteDAO;
import br.com.voo.dal.PassageiroDAO;
import br.com.voo.model.Cliente;
import br.com.voo.model.EstadoCivil;
import br.com.voo.model.Passageiro;
import br.com.voo.model.Pessoa;

public class PassageiroBSTest {

	@Mock
	PassageiroDAO dao;
	Passageiro _passageiro;
	PassageiroBS bs;
	private Date _data;

	@Before
	public void setUp() throws ParseException {
		dao = Mockito.mock(PassageiroDAO.class);
		bs = new PassageiroBS(dao);

		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		String dataNascimento = "11/12/1989";
		_data = data.parse(dataNascimento);

		Pessoa p = new Pessoa("Thiago", "02165072190", "24036864000121",
				"Rua cp 33 quadra 77 lote 11 conjunto primavera", _data, EstadoCivil.Casado,"99999999","thiago@gmail");

		_passageiro = new Passageiro(p);
	}

	@Test
	public void test_Salvar_Passageiro_Valido() {
		try {
			Mockito.when(dao.inserir(_passageiro)).thenReturn(true);
			boolean resultado = bs.salvar(_passageiro);
			assertTrue(resultado);
		} catch (Exception e) {
			assertThat(e).hasMessage("");
		}
	}

	@Test
	public void test_Salvar_Passageiro_Menor_De_Idade() {
		try {
			SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
			String dataNascimento = "11/12/2010";
			_data = data.parse(dataNascimento);

			Pessoa p = new Pessoa("Thiago", "02165072190", "24036864000121",
					"Rua cp 33 quadra 77 lote 11 conjunto primavera", _data, EstadoCivil.Casado,"99999999","thiago@gmail");

			_passageiro = new Passageiro(p);
			Mockito.when(dao.inserir(_passageiro)).thenReturn(true);

			boolean resultado = bs.salvar(_passageiro);
			assertEquals(false, resultado);
		} catch (Exception e) {
			assertThat(e).hasMessage("� nescess�rio informar o respons�vel!");
		}
	}

	@Test
	public void test_Listar_Passageiros() {
		try {
			List<Passageiro> lista = new ArrayList<Passageiro>();
			lista.add(_passageiro);
			String nome = "thi";
			Mockito.when(dao.listar(nome)).thenReturn(lista);
			List<Passageiro> resultado = bs.listar(nome);
			assertEquals(lista, resultado);
		} catch (Exception e) {
			assertThat(e).hasMessage("");
		}
	}

	@Test
	public void test_Excluir_passageiro() {
		try {
			
			Mockito.when(dao.excluir(new Long(1))).thenReturn(true);
			boolean resultado = bs.excluir(new Long(1));
			assertTrue(resultado);
		} catch (Exception e) {
			assertThat(e).hasMessage("");
		}
	}

}
