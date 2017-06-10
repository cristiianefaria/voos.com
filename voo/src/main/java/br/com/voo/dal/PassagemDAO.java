package br.com.voo.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.voo.model.Passagem;
import br.com.voo.util.FactoryConexao;

public class PassagemDAO {

	private Connection conexao;

	public PassagemDAO() {
		conexao = FactoryConexao.getConnection();
	}

	public boolean incluirPassagem(Passagem passagem) throws Exception {
		try {

			
			String sql = "";
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			
			ps.execute();
			ps.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Falha no método salvar");
		}
	}
}
