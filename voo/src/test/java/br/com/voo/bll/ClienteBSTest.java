package br.com.voo.bll;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.voo.dal.ClienteDAO;
import br.com.voo.model.Cliente;
import br.com.voo.model.EstadoCivil;
import br.com.voo.model.Pessoa;
import br.com.voo.model.TipoCliente;

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
		
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		String dataNascimento = "11/12/1989";
		_data = data.parse(dataNascimento);
		
		
		Pessoa p = new Pessoa("Thiago", "02165072190", 
				             "24036864000121", 
				            "Rua cp 33 quadra 77 lote 11 conjunto primavera", 
				            _data, EstadoCivil.Casado,"99999999","thiago@gmail","1234");
		

		_cliente = new Cliente(p);
		_cliente.setMilhagem(200);
		_cliente.setPercentDesconto(10.0);
		_cliente.setTipoCliente(TipoCliente.clienteFinal);		
	}
	@Test
	public void testInserir_Cliente_Valido() {
		try {
			Mockito.when(dao.inserir(_cliente)).thenReturn(true);
			boolean resultado = bs.salvar(_cliente);
			assertTrue(resultado);
		} catch (Exception e) {
			assertThat(e).hasMessage("");
		}
		
	}
	@Test
	public void TestInserir_Cliente_Sem_Nome() {
		try {
			Mockito.when(dao.inserir(_cliente)).thenReturn(true);
			Pessoa p = new Pessoa("", "02165072190", 
		             "24036864000121", 
		            "Rua cp 33 quadra 77 lote 11 conjunto primavera", 
		            _data, EstadoCivil.Casado,"99999999","thiago@gmail","1234");

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
			Mockito.when(dao.inserir(_cliente)).thenReturn(true);
			Pessoa p = new Pessoa("thiago", "02165072190", 
		             "24036864000121", 
		            "Rua cp 33 quadra 77 lote 11 conjunto primavera", 
		            null, EstadoCivil.Casado,"99999999","thiago@gmail","1234");

			_cliente.setPessoa(p);
			_cliente.setPessoa(p);
			boolean resultado = bs.salvar(_cliente);
			assertEquals(false, resultado);
		} catch (Exception e) {
			assertThat(e).hasMessage("Erros encontrados "+"{Data de Nascimento=Data de nascimento n�o informada!}");
		}
		
	}
	@Test
	public void TestInserir_Cliente_Com_Cpf_Valido() {
		try {
			Mockito.when(dao.inserir(_cliente)).thenReturn(true);
			boolean resultado = bs.salvar(_cliente);
			assertEquals(true, resultado);
		} catch (Exception e) {
			assertThat(e).hasMessage("");
		}
		
	}
	@Test
	public void TestInserir_Cliente_Com_Cpf_Invalido() {
		try {
			Mockito.when(dao.inserir(_cliente)).thenReturn(true);
			Pessoa p = new Pessoa("thiago", "021650721390", 
		             "24036864000121", 
		            "Rua cp 33 quadra 77 lote 11 conjunto primavera", 
		            _data, EstadoCivil.Casado,"99999999","thiago@gmail","1234");

			_cliente.setPessoa(p);
			boolean resultado = bs.salvar(_cliente);
			assertEquals(false, resultado);
		} catch (Exception e) {
			assertThat(e).hasMessage("Erros encontrados "+"{cpf=CPF inv�lido!}");
		}
		
	}
	@Test
	public void TestInserir_Cliente_Com_Cnpj_Valido() {
		try {
			Mockito.when(dao.inserir(_cliente)).thenReturn(true);
			Pessoa p = new Pessoa("thiago", "02165072190", 
		             "24036864000121", 
		            "Rua cp 33 quadra 77 lote 11 conjunto primavera", 
		            _data, EstadoCivil.Casado,"99999999","thiago@gmail","123");
			_cliente.setPessoa(p);
			boolean resultado = bs.salvar(_cliente);
			assertEquals(true, resultado);
			
		} catch (Exception e) {
			assertThat(e).hasMessage("Erros encontrados "+"{cnpj=CNPJ inváçlido!}");
		}
		
	}
	@Test
	public void TestBuscar_Lista_De_Pessoa() throws Exception {
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(_cliente);
		String nome = "thiago";
		Mockito.when(dao.listar(nome)).thenReturn(clientes);
		List<Cliente> resultado = bs.listar(nome);
		assertEquals(clientes, resultado);
	}
	@Test
	public void testAlterar_Cliente_Valido() {
		try {
			Mockito.when(dao.inserir(_cliente)).thenReturn(true);
			boolean resultado = bs.salvar(_cliente);
			assertTrue(resultado);
		} catch (Exception e) {
			assertThat(e).hasMessage("");
		}
		
	}
	@Test
	public void TestExcluir_Cliente() throws Exception {
		Long codigo = new Long(1);
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
			assertThat(e).hasMessage("Informe um percentual de desconto v�lido!");
		}
		
	}

}
