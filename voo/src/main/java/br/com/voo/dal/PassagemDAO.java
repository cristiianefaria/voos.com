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
	private PassageiroDAO passageiroDAO;
	private PoltronaDAO poltronaDAO;
	private VooDAO vooDAO;

	public PassagemDAO() {
		conexao = FactoryConexao.getConnection();
		passageiroDAO = new PassageiroDAO();
		poltronaDAO = new PoltronaDAO();
		vooDAO = new VooDAO();
	}

	private boolean incluirPassagem(Passagem passagem, Connection conn) throws Exception {
		try {

			
			String sql = "INSERT INTO public.passagem("
					+ "tipo_cliente, situacao, status_checkin, "
					+ "hash_checkin, codigo_voo, removido, codigo_poltrona, valor) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
		    ps.setString(1, "");
		    ps.setString(2, passagem.getSituacao());
		    ps.setString(3, passagem.getStatusChekIn());
		    ps.setString(4, passagem.getHashCheckIn());
		    ps.setLong(5, passagem.getVoo().getId());
		    ps.setBoolean(6, passagem.getRemovida());
			ps.setLong(7, passagem.getPoltrona().getId());
			ps.setDouble(8, passagem.getValor());
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
				Passagem passagem = new Passagem(new Long(0) ,new Passageiro(),
						              new Passageiro(), Passagem.SituacaoEmberto, "", 
						                                      false, valorPassagem, voo, poltrona);
				incluirPassagem(passagem, conn);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean alterarPassagem(Passagem passagem) throws Exception {
		try {
		
			String sql = "UPDATE public.passagem "
					+ "SET  tipo_cliente=?, situacao=?, status_checkin=?, "
					+ "hash_checkin=?, codigo_voo=?, removido=?, codigo_passageiro=? "
					+ "WHERE codigo = "+passagem.getId()+";";
			PreparedStatement ps =  conexao.prepareStatement(sql);
			
			
		    ps.setString(1, "");
		    ps.setString(2, passagem.getSituacao());
		    ps.setString(3, passagem.getStatusChekIn());
		    ps.setString(4, passagem.getHashCheckIn());
		    ps.setLong(5, passagem.getVoo().getId());
		    ps.setBoolean(6, passagem.getRemovida());
		    ps.setLong(7, passagem.getPassageiro().getId());
			
			ps.executeUpdate();
			ps.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Falha no método Atualizar");
		}
		
		
	}

	public List<Passagem> listarPassagens(Voo voo) throws Exception {
		
		String sql = obterConsulta(voo);
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		List<Passagem> listPassagem = new ArrayList<Passagem>();
		
		while(rs.next()){
			Passagem passagem = new Passagem();
			
			passagem.setId(rs.getLong("codigo"));
			passagem.setHashCheckIn(rs.getString("hash_checkin"));
			passagem.setPassageiro(passageiroDAO.consultar(rs.getLong("codigo_passageiro")));
			passagem.setPoltrona(poltronaDAO.consultar(new Poltrona(rs.getLong("codigo_poltrona"))));
			passagem.setSituacao(rs.getString("situacao"));
			passagem.setRemovida(rs.getBoolean("removido"));
			passagem.setStatusCheckIn(rs.getBoolean("hash_checkin"));
			passagem.setVoo(vooDAO.consultar(new Voo(rs.getLong("codigo_voo"))));
			passagem.setTipoCliente(rs.getString("tipo_cliente"));
			passagem.setValor(rs.getDouble("valor"));
			
			listPassagem.add(passagem);
			
		}
		return listPassagem;
		
	}

	private String obterConsulta(Voo voo) {
		
		String sql = "select * from passagem where "
				+ " codigo_voo = "+ voo.getId()
				+ " and removido ="+false
				+ " and situacao <>'"+Passagem.SituacaoAlocado+"'";
		

		System.out.println(sql);
		return sql;
	
	}

	public Passagem consultarPassagem(Long codigoPassagem) throws Exception {
		
		String sql = "select * from passagem where codigo = "+codigoPassagem+";";
		PreparedStatement ps = conexao.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		Passagem passagem = new Passagem();
		if(rs.next()){
			passagem.setId(rs.getLong("codigo"));
			passagem.setHashCheckIn(rs.getString("hash_checkin"));
			passagem.setPassageiro(passageiroDAO.consultar(rs.getLong("codigo_passageiro")));
			passagem.setPoltrona(poltronaDAO.consultar(new Poltrona(rs.getLong("codigo_poltrona"))));
			passagem.setSituacao(rs.getString("situacao"));
			passagem.setRemovida(rs.getBoolean("removido"));
			passagem.setStatusCheckIn(rs.getBoolean("hash_checkin"));
			passagem.setVoo(vooDAO.consultar(new Voo(rs.getLong("codigo_voo"))));
			passagem.setTipoCliente(rs.getString("tipo_cliente"));
			
		}
		return passagem;
		
	}

	public Passagem consultarPassagemPeloHashCode(String hashCode) throws Exception {
		
		String sql = "select * from passagem where hash_checkin = '"+hashCode+"' and removido = false;";
		PreparedStatement ps = conexao.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		Passagem passagem = new Passagem();
		if(rs.next()){
			passagem.setId(rs.getLong("codigo"));
			passagem.setHashCheckIn(rs.getString("hash_checkin"));
			passagem.setPassageiro(passageiroDAO.consultar(rs.getLong("codigo_passageiro")));
			passagem.setPoltrona(poltronaDAO.consultar(new Poltrona(rs.getLong("codigo_poltrona"))));
			passagem.setSituacao(rs.getString("situacao"));
			passagem.setRemovida(rs.getBoolean("removido"));
			passagem.setStatusCheckIn(rs.getBoolean("hash_checkin"));
			passagem.setVoo(vooDAO.consultar(new Voo(rs.getLong("codigo_voo"))));
			passagem.setTipoCliente(rs.getString("tipo_cliente"));
			
		}
		return passagem;

	}
}
