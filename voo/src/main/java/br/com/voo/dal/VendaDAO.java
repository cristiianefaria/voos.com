package br.com.voo.dal;

import java.sql.Connection;

import br.com.voo.model.Venda;
import br.com.voo.util.FactoryConexao;

public class VendaDAO {

	Connection cnn;

	public VendaDAO(Connection cnn) {
		this.cnn = FactoryConexao.getConnection();
	}
	
	public boolean inclur(Venda venda){
		
		return true;
	}
	
	
	
}
