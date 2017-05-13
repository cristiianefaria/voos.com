package br.com.voo.bll;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
		
		when(dao.salvar()).thenReturn(true);
		
		boolean resultadoEsperado = bs.incluir(poltrona);
		assertTrue(resultadoEsperado);		
	}
	
	@Test
	public void testAlterar() throws SQLException {
		
		when(dao.alterar()).thenReturn(true);
		poltrona.setId(new Long(10));
		boolean resultadoEsperado = bs.incluir(poltrona);
		
		assertTrue(resultadoEsperado);
	}
	
	@Test
	public void testExcluir() throws SQLException{
		
		when(dao.excluir(Mockito.any())).thenReturn(true);
		poltrona.setId(new Long(10));
		boolean resultadoEsperado = bs.excluir(poltrona);
		
		assertTrue(resultadoEsperado);
	}

}
