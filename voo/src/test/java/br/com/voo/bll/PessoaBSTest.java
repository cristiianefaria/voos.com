package br.com.voo.bll;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.voo.dal.PessoaDAO;
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
		_pessoa.setCpf("021.650.721-90");
		_pessoa.setEndereco("Rua cp 33 quadra 77 lote 11 conjunto primavera");
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
	public void TestInserir_Pessoa_Sem_Data_De_Nascimento() {
		Mockito.when(dao.inserir(_pessoa)).thenReturn(true);
		_pessoa.setDataNascimento(null);
		boolean resultado = bs.inserir(_pessoa);
		assertEquals(false, resultado);
	}

}
