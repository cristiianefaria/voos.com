package br.com.voo.bll;

import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.voo.dal.AeronaveDAO;
import br.com.voo.model.Aeronave;
import br.com.voo.model.Poltrona;
import junit.framework.TestCase;

public class AeronaveBSTest extends TestCase{

	private Aeronave aeronave;
	List<Poltrona> poltronas;
	
	@Mock
	private AeronaveDAO dao;
	private AeronaveBS bs;
	
	@Before
	public void setUp() {
		dao = Mockito.mock(AeronaveDAO.class);
		bs = new AeronaveBS();
		Poltrona poltrona = new Poltrona(new Long(1), "A1", 29.90, "Executiva", "Poltrona Conforto",new Aeronave());
		poltronas.add(poltrona);
		poltronas.add(new Poltrona(poltrona));
		poltronas.add(new Poltrona(poltrona));
		
		aeronave = new Aeronave("Boing 777", poltronas);
	}
	
	@Test
	public void testSalvar() throws Exception {
		when(dao.incluir(aeronave)).thenReturn(true);
		
		boolean valorRetornado = bs.salvar(aeronave);
		assertTrue(valorRetornado);
	}
	
	@Test
	public void testAlterar() throws SQLException {
		aeronave.setId(new Long(1));
		when(dao.alterar(aeronave)).thenReturn(true);
		
		boolean valorRetornado = bs.salvar(aeronave);
		assertTrue(valorRetornado);	
	}
	
}
