package br.com.voo.dal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import br.com.voo.model.Cliente;
import br.com.voo.model.EstadoCivil;
import br.com.voo.model.Pessoa;
import br.com.voo.model.TipoCliente;

public class ClienteDAOTest {

	private ClienteDAO dao;
	private Cliente _cliente;
	private Date _data;

	@Before
	public void setUp() throws ParseException {
		dao = new ClienteDAO();
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		String dataNascimento = "11/12/1989";
		_data = data.parse(dataNascimento);

		Pessoa p = new Pessoa("Thiago", "02165072190", "", "Rua cp 33 quadra 77 lote 11 conjunto primavera", _data,
				EstadoCivil.Casado, false,"99999999","thiago@gmail");
		_cliente = new Cliente(p);
		_cliente.setMilhagem(1000);
		_cliente.setPercentDesconto(10.0);
		_cliente.setSenha("123456");
		_cliente.setTipoCliente(TipoCliente.clienteFinal);
		_cliente.setRemovido(false);

	}

	@Test
	public void test_Inserir_Cliente() {

		try {
			boolean resultado = dao.inserir(_cliente);

			assertTrue(resultado);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertThat(e).hasMessage("");

		}

	}
	@Test
	public void test_Obter_Cliente() {

		try {
			
			_cliente.setId(new Long(6));
			Cliente resultado = dao.consultar(new Long(6));
			assertEquals(_cliente.getId(), resultado.getId());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertThat(e).hasMessage("");

		}

	}
	@Test
	public void test_Alterar_Cliente() {

		try {
			
			Pessoa p = new Pessoa(new Long(39),"fayga", "02165072190", "", "Rua cp 33 quadra 77 lote 11 conjunto primavera", _data,
					EstadoCivil.Casado, false,"99999999","thiago@gmail");
			
			_cliente.setId(new Long(1));
			_cliente.setPessoa(p);
			
			boolean resultado = dao.alterar(_cliente);
			assertTrue(resultado);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertThat(e).hasMessage("");

		}

	}
	@Test
	public void test_Excluir_Cliente() {

		try {
			
			
			boolean resultado = dao.exluir(new Long(6));
			assertTrue(resultado);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertThat(e).hasMessage("");

		}

	}

}
