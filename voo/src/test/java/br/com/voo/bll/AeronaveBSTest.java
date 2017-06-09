package br.com.voo.bll;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.mockito.Mock;

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
	
	@Test
	public void testSalvar() throws Exception {
	}
	
	@Test
	public void testAlterar() throws SQLException {
	}
	
}
