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
	Itinerario itinerario2;
	ItinerarioBS bs;
	
	@Before
	public void setUp(){ 
		dao = Mockito.mock(ItinerarioDAO.class);
		itinerario = new Itinerario();
		bs = new ItinerarioBS(dao);
		
		itinerario.setOrigem("Goiania");
		itinerario.setDestino("Gramado");
		
		itinerario2.setId((long) 2);
		itinerario2.setOrigem(null);
		itinerario2.setDestino(null);
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
	public void testSalvarNull() throws Exception{
		itinerario2 = null;
		Mockito.when(dao.incluir(null)).thenReturn(false);
		
		boolean objetoTestado = bs.salvar(itinerario2);
		assertEquals(false, objetoTestado);
	}
	
	
}
