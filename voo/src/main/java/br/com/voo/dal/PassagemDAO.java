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

			
			String sql = "INSERT INTO public.passagem("
					+ "codigo_responsavel, tipo_cliente, situacao, status_checkin, "
					+ "hash_checkin, codigo_voo, removido, codigo_passageiro) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			
		    ps.setLong(1, passagem.getResponsavel().getId());
		    ps.setString(2, "");
		    ps.setString(3, passagem.getSituacao());
		    ps.setString(4, passagem.getStatusChekIn());
		    ps.setString(5, passagem.getHashCheckIn());
		    ps.setLong(6, passagem.getVoo().getId());
		    ps.setBoolean(7, passagem.getRemovida());
		    ps.setLong(8, passagem.getPassageiro().getId());
			
			ps.execute();
			ps.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Falha no método salvar");
		}
	}

	public boolean alterarPassagem(Passagem passagem) {
		
		return false;
		
	}
}
