package br.com.voo.bll;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.voo.dal.PoltronaDAO;
import br.com.voo.model.Poltrona;

public class PoltronaBSTest {

	@Mock
	PoltronaDAO dao;
	
	Poltrona poltrona;
	PoltronaBS bs;
	
	@Before
	public void setUp(){
		dao = mock(PoltronaDAO.class);
		bs = new PoltronaBS(dao);
		poltrona = new Poltrona("A1", new Double(60), "Executiva", "Poltrona conforto");
	}
	
	@Test
	public void testSalvar() throws SQLException {
		
		when(dao.salvar(poltrona)).thenReturn(true);
		
		boolean resultadoEsperado = bs.salvar(poltrona);
		assertTrue(resultadoEsperado);		
	}
	
	@Test
	public void testAlterar() throws SQLException {
		
		when(dao.alterar(poltrona)).thenReturn(true);
		poltrona.setId(new Long(10));
		boolean resultadoEsperado = bs.salvar(poltrona);
		
		assertTrue(resultadoEsperado);
	}
	
	@Test
	public void testExcluir() throws SQLException{
		when(dao.excluir(Mockito.any())).thenReturn(true);
		poltrona.setId(new Long(10));
		boolean resultadoEsperado = bs.excluir(poltrona);
		
		assertTrue(resultadoEsperado);
	}
	
	// TESTS COM OBJETOS NULOS

	
	@Test
	public void testSalvarNUll() throws SQLException {
		try {
			poltrona = null;
			when(dao.salvar(null)).thenReturn(false);
			
			bs.salvar(poltrona);
		}catch(Exception e) {
			assertThat(e).hasMessage("Poltrona enviada para ser salva está null");
		}
	}
	
	@Test
	public void testAlterarNull() throws SQLException {
		try {
			poltrona = null;
			when(dao.alterar(null)).thenReturn(false);
			bs.salvar(poltrona);
		}catch(Exception e) {
			assertThat(e).hasMessage("Poltrona enviada para ser salva está null");
		}
	}
	
	@Test
	public void testExcluirNull() throws SQLException{
		try {
			poltrona = null;
			when(dao.excluir(Mockito.any())).thenReturn(true);
			bs.excluir(poltrona);
		}catch(Exception e) {
			assertThat(e).hasMessage("Poltrona enviada para exclusão esta null");
		}
	}
	
	
	// TEST COM PARAMETROS NULOS
	
	@Test
	public void testSalvarIdNull() throws SQLException {
		poltrona = new Poltrona(null, "descricao", 29.90, "Executiva", "Poltrona conforto");
		when(dao.salvar(poltrona)).thenReturn(true);
		
		boolean resultadoEsperado = bs.salvar(poltrona);
		assertTrue(resultadoEsperado);		
	}
	
	@Test
	public void testSalvarDescricaoNull() throws SQLException {
		poltrona = new Poltrona(null, 29.90, "Executiva", "Poltrona conforto");
		when(dao.salvar(poltrona)).thenReturn(true);
		
		boolean resultadoEsperado = bs.salvar(poltrona);
		assertTrue(resultadoEsperado);		
	}
	
	@Test
	public void testSalvarValorNull() throws SQLException {
		poltrona = new Poltrona("descricao", null, "Executiva", "Poltrona conforto");
		when(dao.salvar(poltrona)).thenReturn(true);
		
		boolean resultadoEsperado = bs.salvar(poltrona);
		assertTrue(resultadoEsperado);		
	}
	
	@Test
	public void testSalvarClasseNull() throws SQLException {
		poltrona = new Poltrona("descricao", 29.90, null, "Poltrona conforto");
		when(dao.salvar(poltrona)).thenReturn(true);
		
		boolean resultadoEsperado = bs.salvar(poltrona);
		assertTrue(resultadoEsperado);		
	}
	
	@Test
	public void testSalvarDetalhesNull() throws SQLException {
		poltrona = new Poltrona("descricao", 29.90, "Executiva", null);
		when(dao.salvar(poltrona)).thenReturn(true);
		
		boolean resultadoEsperado = bs.salvar(poltrona);
		assertTrue(resultadoEsperado);		
	}
	
	@Test
	public void testExcluirIdVazio() throws SQLException{
		try {
			when(dao.excluir(Mockito.any())).thenReturn(true);
			bs.excluir(poltrona);
		}catch(Exception e) {
			assertThat(e).hasMessage("Poltrona enviada para exclusão esta null");
		}
	}
	
}
