package br.com.voo.dal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.omg.CORBA._PolicyStub;

import br.com.voo.model.EstadoCivil;
import br.com.voo.model.Passageiro;
import br.com.voo.model.Pessoa;

public class PassageiroDAOTest {

	private Date _data;
	private Passageiro _passageiro;
	PassageiroDAO dao;

	@Before
	public void setUp() throws ParseException {

		dao = new PassageiroDAO();
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		String dataNascimento = "11/12/1989";
		_data = data.parse(dataNascimento);

		Pessoa p = new Pessoa("Thiago", "02165072190", "", "Rua cp 33 quadra 77 lote 11 conjunto primavera", _data,

				EstadoCivil.Casado, false);
		_passageiro = new Passageiro(p);

		Pessoa p2 = new Pessoa("fayga", "03793069133", "", "Rua cp 33 quadra 77 lote 11 conjunto primavera", _data,
				EstadoCivil.Casado, false);

		Passageiro responsavel = new Passageiro(p2);

		_passageiro.setResponsavel(responsavel);

	}

	@Test
	public void test_Inserir_Passageiro() {
		try {

			// Mockito.when(dao.salvar(_passageiro)).thenReturn(true);
			boolean resultado = dao.inserir(_passageiro);

			assertTrue(resultado);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertThat(e).hasMessage("");

		}
	}

	@Test
	public void test_Atualizar_Passageiro() {
		try {
			Pessoa p3 = new Pessoa(6, "Thiago", "02165072190", "", "Rua cp 33 quadra 77 lote 11 conjunto primavera",
					_data, EstadoCivil.Casado, true);
			_passageiro = new Passageiro(p3);
			_passageiro.setId(Long.parseLong("1"));
			boolean resultado = dao.atualiza(_passageiro);
			assertTrue(resultado);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertThat(e).hasMessage("");

		}

	}

	@Test
	public void test_Excluir_Passageiro() {
		try {

			// Mockito.when(dao.salvar(_passageiro)).thenReturn(true);
			Pessoa p = new Pessoa("Thiago", "02165072190", "", "Rua cp 33 quadra 77 lote 11 conjunto primavera", _data,
					EstadoCivil.Casado, true);
			_passageiro = new Passageiro(p);
			_passageiro.setRemovido(true);
			_passageiro.setId(Long.parseLong("1"));
			boolean resultado = dao.excluir(_passageiro);
			assertTrue(resultado);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertThat(e).hasMessage("");

		}
	}

}
