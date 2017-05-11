package br.com.voo.bll;

import java.util.Date;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.voo.dal.ItinerarioDAO;
import br.com.voo.model.Cliente;
import junit.framework.TestCase;

public class ItinerarioBSTest extends TestCase{

	@Mock
	ItinerarioDAO dao;
	
	@Before
	public void setUp(){
		dao = Mockito.mock(ItinerarioDAO.class);
		
	}

}
