package br.com.voo.bll;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.voo.dal.PessoaDAO;
import br.com.voo.model.Cnpj;
import br.com.voo.model.Cpf;
import br.com.voo.model.EstadoCivil;
import br.com.voo.model.Pessoa;

public class PessoaBSTest {

	Pessoa _pessoa;
	@Mock
	PessoaDAO dao;
	
	PessoaBS bs;
	@Before
	public void setUp() throws ParseException {
		
		dao = Mockito.mock(PessoaDAO.class);
		bs = new PessoaBS(dao);
		_pessoa = new Pessoa();
		
		_pessoa.setNome("Thiago Rodrigues");
		_pessoa.setCpf(new Cpf("02165072190"));
		_pessoa.setEndereco("Rua cp 33 quadra 77 lote 11 conjunto primavera");
		_pessoa.setPessoaFisisca(true);
		_pessoa.setCnpj(new Cnpj("24036864000121"));
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		String dataNascimento = "11/12/1989";
		_pessoa.setDataNascimento(data.parse(dataNascimento));
		_pessoa.setEstadoCivil(EstadoCivil.Casado);
		
	}
	@Test
	public void testInserir_Pessoa_Valida() {
		Mockito.when(dao.inserir(_pessoa)).thenReturn(true);
		boolean resultado = bs.inserir(_pessoa);
		assertTrue(resultado);
	}
	@Test
	public void TestInserir_Pessoa_Sem_Nome() {
		Mockito.when(dao.inserir(_pessoa)).thenReturn(true);
		_pessoa.setNome("");
		boolean resultado = bs.inserir(_pessoa);
		assertEquals(false, resultado);
	}
	@Test
	public void TestInserir_Pessoa_Sem_Data_De_Nascimento() {
		Mockito.when(dao.inserir(_pessoa)).thenReturn(true);
		_pessoa.setDataNascimento(null);
		boolean resultado = bs.inserir(_pessoa);
		assertEquals(false, resultado);
	}
	@Test
	public void TestInserir_Pessoa_Com_Cpf_Valido() {
		Mockito.when(dao.inserir(_pessoa)).thenReturn(true);
		boolean resultado = bs.inserir(_pessoa);
		assertEquals(true, resultado);
	}
	@Test
	public void TestInserir_Pessoa_Com_Cpf_Invalido() {
		Mockito.when(dao.inserir(_pessoa)).thenReturn(true);
		_pessoa.setCpf(new Cpf("12354622996"));
		boolean resultado = bs.inserir(_pessoa);
		assertEquals(false, resultado);
	}
	@Test
	public void TestInserir_Pessoa_Com_Cnpj_Valido() {
		Mockito.when(dao.inserir(_pessoa)).thenReturn(true);
		_pessoa.setPessoaFisisca(false);
		boolean resultado = bs.inserir(_pessoa);
		assertEquals(true, resultado);
	}
	
	@Test
	public void TestInserir_Pessoa_Com_Cnpj_Invalido() {
		Mockito.when(dao.inserir(_pessoa)).thenReturn(true);
		_pessoa.setPessoaFisisca(false);
		_pessoa.setCnpj(new Cnpj("0024036864000112"));
		boolean resultado = bs.inserir(_pessoa);
		assertEquals(false, resultado);
	}

	@Test
	public void TestBuscar_Lista_De_Pessoa() {
		Pessoa pessoa = new Pessoa();
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		pessoas.add(_pessoa);
		Mockito.when(dao.buscar(pessoa)).thenReturn(pessoas);
		List<Pessoa> resultado = bs.buscar(pessoa);
		assertEquals(pessoas, resultado);
	}
	@Test
	public void TestExcluir_Pessoa() {
		int codigo = 1;
		Mockito.when(dao.exluir(codigo)).thenReturn(true);
		boolean resultado = bs.excluir(codigo);
		assertTrue(resultado);
	}
	
	
	

}
