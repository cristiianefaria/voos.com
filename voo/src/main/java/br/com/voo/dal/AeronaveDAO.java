package br.com.voo.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.voo.model.Aeronave;
import br.com.voo.model.Poltrona;
import br.com.voo.util.FactoryConexao;

public class AeronaveDAO {

	PoltronaDAO poltronaDAO;
	Connection cnn;
	
	public AeronaveDAO() {
		this.poltronaDAO = new PoltronaDAO();
		cnn = FactoryConexao.getConnection();
	}

	public boolean incluir(Aeronave aeronave) throws SQLException {
		String sql = "INSERT INTO aeronave (descricao) VALUES (?) RETURNING codigo";
		
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setString(1, aeronave.getDescricao());
		ResultSet rs = ps.executeQuery();
		rs.next();
		aeronave.setId(rs.getLong("codigo"));

		rs.close();
		ps.close();
		
		for(Poltrona poltrona : aeronave.getPoltronas()) {
			poltronaDAO.salvar(poltrona);
		}
		return true;
	}

	public boolean alterar(Aeronave aeronave) throws SQLException {
		String sql = "UPDATE aeronave SET descricao = ? WHERE codigo = ?";

		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setString(1, aeronave.getDescricao());
		ps.setLong(2, aeronave.getId());
		
		ps.execute();
		ps.close();
		
		return true;
	}
	
	public Aeronave consultar(Aeronave aeronave)throws SQLException {
		String sql = "SELECT * FROM aeronave WHERE codigo = ?";
		
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setLong(1, aeronave.getId());

		ResultSet rs = ps.executeQuery();
		Aeronave retorno = new Aeronave();
		
		if(rs.next()) {
			retorno = new Aeronave(rs.getLong("codigo"), 
					rs.getString("descricao"),
					poltronaDAO.listar(new Poltrona(aeronave)));
		}
		
		rs.close();
		ps.close();
		
		return retorno;
	}
	
	public List<Aeronave> listar()throws SQLException{
    	String sql = "SELECT * FROM aeronave";
    	
    	PreparedStatement ps = cnn.prepareStatement(sql);
    	ResultSet rs = ps.executeQuery();
    	List<Aeronave> aeronaves = new ArrayList<Aeronave>();
    	while(rs.next()) {
    		Aeronave aeronaveTemp = new Aeronave(
    				rs.getLong("codigo"),rs.getString("descricao"),null);
    		
    		aeronaveTemp.cadastrarPoltrona(
    				poltronaDAO.listar(new Poltrona(aeronaveTemp)));
    		
    		aeronaves.add(aeronaveTemp);
    	}
    	
		rs.close();
		ps.close();
    	
		return aeronaves;
    }

	public boolean remover(Aeronave aeronave) throws SQLException {
		String sql = "DELETE FROM aeronave WHERE codigo = ?";
		
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setLong(1, aeronave.getId());
		ps.execute();
		
		ps.close();
		
		return true;
	}
}
