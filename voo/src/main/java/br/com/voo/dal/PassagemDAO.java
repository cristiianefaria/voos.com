package br.com.voo.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.voo.model.Passageiro;
import br.com.voo.model.Passagem;
import br.com.voo.model.Poltrona;
import br.com.voo.model.Voo;
import br.com.voo.util.FactoryConexao;

public class PassagemDAO {

	private Connection conexao;

	public PassagemDAO() {
		conexao = FactoryConexao.getConnection();
	}

	private boolean incluirPassagem(Passagem passagem, Connection conn) throws Exception {
		try {

			
			String sql = "INSERT INTO public.passagem("
					+ "tipo_cliente, situacao, status_checkin, "
					+ "hash_checkin, codigo_voo, removido) "
					+ "VALUES (?, ?, ?, ?, ?, ?);";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
		    ps.setString(1, "");
		    ps.setString(2, passagem.getSituacao());
		    ps.setString(3, passagem.getStatusChekIn());
		    ps.setString(4, passagem.getHashCheckIn());
		    ps.setLong(5, passagem.getVoo().getId());
		    ps.setBoolean(6, passagem.getRemovida());
			
			ps.execute();
			
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Falha no método inserir passageiro");
		}
	}
	public void incluirPassagens(Voo voo, Connection conn) {
		
		try {
			for (Poltrona poltrona : voo.getAeronave().getPoltronas()) {

				Double valorPassagem = voo.getItinerario().getValor() + poltrona.getValor();
				Passagem passagem = new Passagem(new Long(0) ,new Passageiro(), new Passageiro(), "", "", false, valorPassagem, voo);
				incluirPassagem(passagem, conn);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean alterarPassagem(Passagem passagem) throws Exception {
		try {
		
			String sql = "UPDATE public.passagem"
					+ "SET codigo_responsavel=?, tipo_cliente=?, situacao=?, status_checkin=?, "
					+ "hash_checkin=?, codigo_voo=?, removido=?, codigo_passageiro=? "
					+ "WHERE codigo = "+passagem.getId()+";";
			PreparedStatement ps =  conexao.prepareStatement(sql);
			
		    ps.setLong(1, passagem.getResponsavel().getId());
		    ps.setString(2, "");
		    ps.setString(3, passagem.getSituacao());
		    ps.setString(4, passagem.getStatusChekIn());
		    ps.setString(5, passagem.getHashCheckIn());
		    ps.setLong(6, passagem.getVoo().getId());
		    ps.setBoolean(7, passagem.getRemovida());
		    ps.setLong(8, passagem.getPassageiro().getId());
			
			ps.executeUpdate();
			ps.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Falha no método Atualizar");
		}
		
		
	}

	public List<Passagem> listarPassagens(Voo voo) throws SQLException {
		
		String sql = "";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		List<Passagem> listPassagem = new ArrayList<Passagem>();
		
		while(rs.next()){
			
			
			
			
		}
		return listPassagem;
		
		
	}
}
