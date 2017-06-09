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
		
		itinerario.setOrigem("Goiania");
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
		itinerario.setId(new Long(2));
		Mockito.when(dao.alterar(itinerario)).thenReturn(true);
		
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
	
	@Test
	public void testSalvarOrigemVazio() throws Exception{
		itinerario.setOrigem("");
		Mockito.when(dao.incluir(itinerario)).thenReturn(true);
		
		boolean objetoTestado = bs.salvar(itinerario);
		assertEquals(false, objetoTestado);
	}
	
	@Test
	public void testSalvarDestinoVazio() throws Exception{
		itinerario.setDestino("");
		Mockito.when(dao.incluir(itinerario)).thenReturn(true);
		
		boolean objetoTestado = bs.salvar(itinerario);
		assertEquals(false, objetoTestado);
	}
	
	@Test
	public void testSalvarOrigemNull() throws Exception{
		itinerario.setOrigem(null);
		Mockito.when(dao.incluir(itinerario)).thenReturn(true);
		
		boolean objetoTestado = bs.salvar(itinerario);
		assertEquals(false, objetoTestado);
	}
	
	@Test
	public void testSalvarDestinoNull() throws Exception{
		itinerario.setDestino(null);
		Mockito.when(dao.incluir(itinerario)).thenReturn(true);
		
		boolean objetoTestado = bs.salvar(itinerario);
		assertEquals(false, objetoTestado);
	}
	
	@Test
	public void testSalvarOrigemEDestinoNull() throws Exception{
		itinerario.setOrigem(null);
		itinerario.setDestino(null);

		Mockito.when(dao.incluir(itinerario)).thenReturn(true);
		
		boolean objetoTestado = bs.salvar(itinerario);
		assertEquals(false, objetoTestado);
	}
	
	@Test
	public void testSalvarOrigemEDestinoVazio() throws Exception{
		itinerario.setDestino("");
		itinerario.setDestino("");
		Mockito.when(dao.incluir(itinerario)).thenReturn(true);
		
		boolean objetoTestado = bs.salvar(itinerario);
		assertEquals(false, objetoTestado);
	}
	
}
