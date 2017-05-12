package br.com.voo.dal;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import br.com.voo.model.Poltrona;
import br.com.voo.util.FactoryConexao;

public class PoltronaDAO {
	
	final static Logger log = Logger.getLogger(PoltronaDAO.class.getName());

	private Connection conexao;

    public PoltronaDAO() {
        conexao = FactoryConexao.getConnection();
    }
    
    public boolean salvar() throws SQLException{
    	return true;
    }
    
    public boolean consultar() throws SQLException{
    	return true;
    }

	public boolean alterar() throws SQLException{

		return true;
	}

	public boolean excluir(Poltrona poltrona)throws SQLException {
		
		return true;
	}
    
    
	
}
