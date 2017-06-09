package br.com.voo.bll;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.voo.dal.PoltronaDAO;
import br.com.voo.model.Aeronave;
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
		poltrona = new Poltrona("A1", new Double(60), "Executiva", "Poltrona conforto", new Aeronave());
	}
	
	@Test
	public void testSalvar() throws Exception {
		
		when(dao.salvar(poltrona)).thenReturn(true);
		
		boolean resultadoEsperado = bs.salvar(poltrona);
		assertTrue(resultadoEsperado);		
	}
	
	@Test
	public void testAlterar() throws Exception {
		
		when(dao.alterar(poltrona)).thenReturn(true);
		poltrona.setId(new Long(10));
		boolean resultadoEsperado = bs.salvar(poltrona);
		
		assertTrue(resultadoEsperado);
	}
	
	@Test
	public void testExcluir() throws Exception{
		when(dao.alterar(Mockito.any())).thenReturn(true);
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
			assertThat(e).hasMessage("Poltrona enviada para ser salva esta null");
		}
	}
	
	@Test
	public void testAlterarNull() throws SQLException {
		try {
			poltrona = null;
			when(dao.alterar(null)).thenReturn(false);
			bs.salvar(poltrona);
		}catch(Exception e) {
			assertThat(e).hasMessage("Poltrona enviada para ser salva esta null");
		}
	}
	
	@Test
	public void testExcluirNull() throws SQLException{
		try {
			poltrona = null;
			when(dao.alterar(Mockito.any())).thenReturn(true);
			bs.excluir(poltrona);
		}catch(Exception e) {
			assertThat(e).hasMessage("Poltrona enviada para exclusao esta null");
		}
	}
	
	
	// TEST COM PARAMETROS NULOS
	
	@Test
	public void testSalvarIdNull() throws Exception {
		poltrona = new Poltrona(null, "descricao", 29.90, "Executiva", "Poltrona conforto", new Aeronave());
		when(dao.salvar(poltrona)).thenReturn(true);
		
		boolean resultadoEsperado = bs.salvar(poltrona);
		assertTrue(resultadoEsperado);		
	}
	
	@Test
	public void testSalvarDescricaoNull() throws Exception {
		poltrona = new Poltrona(null, 29.90, "Executiva", "Poltrona conforto",new Aeronave());
		when(dao.salvar(poltrona)).thenReturn(true);
		
		boolean resultadoEsperado = bs.salvar(poltrona);
		assertTrue(resultadoEsperado);		
	}
	
	@Test
	public void testSalvarValorNull() throws Exception {
		poltrona = new Poltrona("descricao", null, "Executiva", "Poltrona conforto",new Aeronave());
		when(dao.salvar(poltrona)).thenReturn(true);
		
		boolean resultadoEsperado = bs.salvar(poltrona);
		assertTrue(resultadoEsperado);		
	}
	
	@Test
	public void testSalvarClasseNull() throws Exception {
		poltrona = new Poltrona("descricao", 29.90, null, "Poltrona conforto", new Aeronave());
		when(dao.salvar(poltrona)).thenReturn(true);
		
		boolean resultadoEsperado = bs.salvar(poltrona);
		assertTrue(resultadoEsperado);		
	}
	
	@Test
	public void testSalvarDetalhesNull() throws Exception {
		poltrona = new Poltrona("descricao", 29.90, "Executiva", null, new Aeronave());
		when(dao.salvar(poltrona)).thenReturn(true);
		
		boolean resultadoEsperado = bs.salvar(poltrona);
		assertTrue(resultadoEsperado);		
	}
	
	@Test
	public void testExcluirIdVazio() throws SQLException{
		try {
			when(dao.alterar(Mockito.any())).thenReturn(true);
			bs.excluir(poltrona);
		}catch(Exception e) {
			assertThat(e).hasMessage("Não é possivel excluir uma poltrona sem ID");
		}
	}
	
}
