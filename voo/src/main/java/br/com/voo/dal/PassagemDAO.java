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

	public boolean incluirPassagem() throws Exception {
		try {

			String sql = "INSERT INTO public.passagem("
					+ "codigo_pessoa, codigo_responsavel, tipo_cliente, situacao, "
					+ "status_checkin, hash_checkin, codigo_voo, removido) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setLong(1, x);
			
			ps.execute();
			ps.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Falha no método salvar");
		}
	}
}
