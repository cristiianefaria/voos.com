package br.com.voo.bll;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.crypto.Data;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.voo.dal.ClienteDAO;
import br.com.voo.model.Cliente;
import br.com.voo.model.Cpf;
import br.com.voo.model.EstadoCivil;
import br.com.voo.model.Pessoa;

public class ClienteBSTest {

	@Mock
	ClienteDAO dao;
	Cliente _cliente;
	ClienteBS bs;
	private Date _data;
	
	@Before
	public void setUp() throws ParseException{
		dao = Mockito.mock(ClienteDAO.class);
		bs = new ClienteBS(dao);
		_cliente = new Cliente();
		
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		String dataNascimento = "11/12/1989";
		_data = data.parse(dataNascimento);
		
		
		Pessoa p = new Pessoa("Thiago", "02165072190", 
				             "24036864000121", 
				            "Rua cp 33 quadra 77 lote 11 conjunto primavera", 
				            _data, EstadoCivil.Casado, false);
		_cliente.setMilhagem(200);
		_cliente.setPercentDesconto(10.0);
		_cliente.setPessoa(p);
		_cliente.setSenha("1234");
		_cliente.setTipoCliente("Cliente Final");		
	}
	@Test
	public void testInserir_Cliente_Valido() {
		try {
			Mockito.when(dao.salvar(_cliente)).thenReturn(true);
			boolean resultado = bs.salvar(_cliente);
			assertTrue(resultado);
		} catch (Exception e) {
			assertThat(e).hasMessage("");
		}
		
	}
	@Test
	public void TestInserir_Cliente_Sem_Nome() {
		try {
			Mockito.when(dao.salvar(_cliente)).thenReturn(true);
			Pessoa p = new Pessoa("", "02165072190", 
		             "24036864000121", 
		            "Rua cp 33 quadra 77 lote 11 conjunto primavera", 
		            _data, EstadoCivil.Casado, false);

			_cliente.setPessoa(p);
			boolean resultado = bs.salvar(_cliente);
			assertEquals(false, resultado);
		} catch (Exception e) {
			assertThat(e).hasMessage("Erros encontrados "+"{nome=Informe um nome}");
		}
		
	}
	@Test
	public void TestInserir_Cliente_Sem_Data_De_Nascimento() {
		try {
			Mockito.when(dao.salvar(_cliente)).thenReturn(true);
			Pessoa p = new Pessoa("thiago", "02165072190", 
		             "24036864000121", 
		            "Rua cp 33 quadra 77 lote 11 conjunto primavera", 
		            null, EstadoCivil.Casado, false);

			_cliente.setPessoa(p);
			_cliente.setPessoa(p);
			boolean resultado = bs.salvar(_cliente);
			assertEquals(false, resultado);
		} catch (Exception e) {
			assertThat(e).hasMessage("Erros encontrados "+"{Data de Nascimento=Data de nascimento não informada!}");
		}
		
	}
	@Test
	public void TestInserir_Cliente_Com_Cpf_Valido() {
		try {
			Mockito.when(dao.salvar(_cliente)).thenReturn(true);
			boolean resultado = bs.salvar(_cliente);
			assertEquals(true, resultado);
		} catch (Exception e) {
			assertThat(e).hasMessage("");
		}
		
	}
	@Test
	public void TestInserir_Cliente_Com_Cpf_Invalido() {
		try {
			Mockito.when(dao.salvar(_cliente)).thenReturn(true);
			Pessoa p = new Pessoa("thiago", "021650721390", 
		             "24036864000121", 
		            "Rua cp 33 quadra 77 lote 11 conjunto primavera", 
		            _data, EstadoCivil.Casado, false);

			_cliente.setPessoa(p);
			boolean resultado = bs.salvar(_cliente);
			assertEquals(false, resultado);
		} catch (Exception e) {
			assertThat(e).hasMessage("Erros encontrados "+"{cpf=CPF inválido!}");
		}
		
	}
	@Test
	public void TestInserir_Cliente_Com_Cnpj_Valido() {
		try {
			Mockito.when(dao.salvar(_cliente)).thenReturn(true);
			Pessoa p = new Pessoa("thiago", "02165072190", 
		             "24036864000121", 
		            "Rua cp 33 quadra 77 lote 11 conjunto primavera", 
		            _data, EstadoCivil.Casado, false);
			_cliente.setPessoa(p);
			boolean resultado = bs.salvar(_cliente);
			assertEquals(true, resultado);
			
		} catch (Exception e) {
			assertThat(e).hasMessage("Erros encontrados "+"{cnpj=CNPJ inválido!}");
		}
		
	}
	@Test
	public void TestBuscar_Lista_De_Pessoa() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(_cliente);
		Mockito.when(dao.buscar()).thenReturn(clientes);
		List<Cliente> resultado = bs.buscar();
		assertEquals(clientes, resultado);
	}
	@Test
	public void testAlterar_Cliente_Valido() {
		try {
			Mockito.when(dao.salvar(_cliente)).thenReturn(true);
			boolean resultado = bs.salvar(_cliente);
			assertTrue(resultado);
		} catch (Exception e) {
			assertThat(e).hasMessage("");
		}
		
	}
	@Test
	public void TestExcluir_Cliente() {
		int codigo = 1;
		Mockito.when(dao.exluir(codigo)).thenReturn(true);
		boolean resultado = bs.excluir(codigo);
		assertTrue(resultado);
	}
	@Test
	public void TestExcluir_Cliente_Com_Percentual_Desconto_Invalido() {
		try {
			_cliente.setPercentDesconto(101.0);
			boolean resultado = bs.salvar(_cliente);
			assertEquals(false, resultado);
		} catch (Exception e) {
			assertThat(e).hasMessage("Informe um percentual de desconto válido!");
		}
		
	}

}
