package br.com.voo.bll;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.voo.dal.ItinerarioDAO;
import br.com.voo.model.Itinerario;
import junit.framework.TestCase;

public class ItinerarioBSTest extends TestCase{

	@Mock
	ItinerarioDAO dao;
	Itinerario itinerario;
	ItinerarioBS bs;
	
	@Before
	public void setUp(){ 
		dao = Mockito.mock(ItinerarioDAO.class);
		itinerario = new Itinerario();
		bs = new ItinerarioBS(dao);
		
		itinerario.setOrigem(null);
		itinerario.setDestino("Gramado");
	}
	
	@Test
	public void testSalvar() throws Exception{
		Mockito.when(dao.incluir(itinerario)).thenReturn(true);
		
		boolean objetoTestado = bs.salvar(itinerario);
		assertEquals(true, objetoTestado);
	}
	
	@Test
	public void testAlterar() throws Exception{
		Mockito.when(dao.alterar(itinerario)).thenReturn(true);
		
		boolean objetoTestado = bs.salvar(itinerario);
		assertEquals(true, objetoTestado);
	}
	
	@Test
	public void testDeletar() throws Exception{
		Mockito.when(dao.incluir(itinerario)).thenReturn(true);
		
		boolean objetoTestado = bs.salvar(itinerario);
		assertEquals(true, objetoTestado);
	}
	
	@Test
	public void testListarTodos() throws Exception{
		Mockito.when(dao.incluir(itinerario)).thenReturn(true);
		
		boolean objetoTestado = bs.salvar(itinerario);
		assertEquals(true, objetoTestado);
	}
	
	
	@Test
	public void testSalvarNull() throws Exception{
		itinerario = null;
		Mockito.when(dao.incluir(null)).thenReturn(false);

		
		boolean objetoTestado = bs.salvar(itinerario);
		assertEquals(false, objetoTestado);
	}
	
	
}
