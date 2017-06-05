package br.com.voo.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.voo.model.Contato;

public class ContatoDAO {

	
	private Long _codigoDadosContato;
	
	public ContatoDAO() {
		
	}
	
	public boolean inserir(Contato contato, Connection conexao) throws Exception{
		
		try {
			conexao.setAutoCommit(false);
			
			if(inserirDadosContato(conexao, contato)){
				PreparedStatement ps = conexao.prepareStatement("INSERT INTO public.contato("
						+ " codigo_passageiro, codigo_dados_contato, relacao, removido)"
						+ "VALUES (?, ?, ?, ?);");
				
				ps.setLong(1, contato.getPassageiro().getId());
				ps.setLong(2, _codigoDadosContato);
				ps.setString(3, contato.getRelacao());
				ps.setBoolean(4, contato.getRemovida());
				
				ps.execute();
				conexao.commit();
				return true;
				
			}
			return false;
			
		} catch (Exception e) {
			conexao.rollback();
			throw new Exception(e.getMessage());
		}
	}

	private boolean inserirDadosContato(Connection con, Contato contato) {

		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO public.dados_contato("
					+ "nome, telefone_1, telefone_2) VALUES (?, ?, ?) RETURNING codigo;");
			
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getTelefone1());
			ps.setString(3, contato.getTelefone2());
			
			ps.executeQuery();
			ResultSet rs = ps.getResultSet();
			_codigoDadosContato = rs.getLong("codigo");
			
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
		
	}

	
}
