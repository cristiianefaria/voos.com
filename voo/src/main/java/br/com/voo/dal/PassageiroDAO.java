package br.com.voo.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
<<<<<<< HEAD
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import br.com.voo.model.EstadoCivil;
=======
import java.util.List;

>>>>>>> criando persitencia de passageiro
import br.com.voo.model.Passageiro;
import br.com.voo.model.Pessoa;
import br.com.voo.util.FactoryConexao;

public class PassageiroDAO {

	private Connection conexao;
	private PessoaDAO pessoa;

	public PassageiroDAO() {
		conexao = FactoryConexao.getConnection();
		pessoa = new PessoaDAO();
	}

<<<<<<< HEAD
	public boolean atualiza(Passageiro _passageiro) throws Exception {

		try {
			conexao.setAutoCommit(false);
			if (pessoa.Alterar(_passageiro.getPessoa(), conexao)) {

				PreparedStatement ps = conexao.prepareStatement("UPDATE public.passageiro "
						+ "set responsavel=?, removido=? WHERE codigo = " + _passageiro.getId());

				ps.setLong(1, validaSeResponsavelNulo(_passageiro.getResponsavel()).getId());
				ps.setBoolean(2, _passageiro.isRemovido());

				ps.executeUpdate();
				conexao.commit();
				return true;
			} else
				conexao.rollback();

=======
	public boolean salvar(Passageiro _passageiro) throws Exception {

		if (_passageiro.getId() == 0)
			return inserir(_passageiro);
		else
			return atualiza(_passageiro);

	}

	private boolean atualiza(Passageiro _passageiro) throws Exception {
		
		try {
			conexao.setAutoCommit(false);
			if(pessoa.Alterar(_passageiro.getPessoa(), conexao)){
				
				PreparedStatement ps = conexao
						.prepareStatement("UPDATE public.passageiro"
								+ "SET codigo_pessoa=?, "
								+ "responsavel=?, removido"
								+ "WHERE codigo = "+_passageiro.getId());
				ps.setLong(1, _passageiro.getPessoa().getId());
				ps.setLong(2, _passageiro.getResponsavel().getId());
				ps.setBoolean(3, false);
				
				ps.executeUpdate();
				conexao.commit();
				return true;
			}
			else
				conexao.rollback();
			
>>>>>>> criando persitencia de passageiro
			return false;
		} catch (Exception e) {
			conexao.rollback();
			throw new Exception(e.getMessage());
		}
	}

<<<<<<< HEAD
	private Passageiro validaSeResponsavelNulo(Passageiro responsavel) {

		return responsavel != null ? responsavel : new Passageiro();
	}

	public boolean inserir(Passageiro _passageiro) throws Exception {
=======
	private boolean inserir(Passageiro _passageiro) throws Exception {
>>>>>>> criando persitencia de passageiro

		try {
			PreparedStatement ps = conexao.prepareStatement(
					"INSERT INTO public.passageiro(codigo_pessoa, responsavel, removido)" + "VALUES (?, ?, ?);");

			conexao.setAutoCommit(false);
<<<<<<< HEAD

			if (pessoa.salvar(_passageiro.getPessoa(), conexao)) {
				long codigoPessoa = pessoa.consultar(_passageiro.getPessoa().getCpf().getNumero(),
						_passageiro.getPessoa().getCnpj().getNumero(), conexao).getId();

				ps.setLong(1, codigoPessoa);
				if (_passageiro.getResponsavel() != null)
					ps.setLong(2, _passageiro.getResponsavel().getPessoa().getId());
				ps.setBoolean(3, false);

				ps.execute();
				conexao.commit();
				return true;

			} else
				conexao.rollback();

			return false;
=======
			
			if (pessoa.salvar(_passageiro.getPessoa(), conexao)) {
				long codigoPessoa = pessoa.consultar(_passageiro.getPessoa().getCpf().getNumero(),
						_passageiro.getPessoa().getCnpj().getNumero(), conexao).getId();
		
				ps.setLong(1, codigoPessoa);
				if(_passageiro.getResponsavel() != null)
				ps.setLong(2, _passageiro.getResponsavel().getPessoa().getId());
				ps.setBoolean(3, false);
				
				ps.execute();
				conexao.commit();
				return true;
				
			}
			else
				conexao.rollback();
			
			return false;
			
>>>>>>> criando persitencia de passageiro

		} catch (Exception e) {
			conexao.rollback();
			throw new Exception(e.getMessage());
		}

	}

	public List<Passageiro> listar() {
<<<<<<< HEAD
=======
		// TODO Auto-generated method stub
		return null;
	}

	public boolean excluir(int id) {
>>>>>>> criando persitencia de passageiro
		// TODO Auto-generated method stub
		return null;
	}

	public boolean excluir(Passageiro _passageiro) throws Exception {
		try {
			Passageiro passageiro = buscar(_passageiro.getId());
			passageiro.setRemovido(true);
			return atualiza(passageiro);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public Passageiro buscar(Long id) throws Exception {
		try {
			PreparedStatement ps = 
					  conexao.prepareStatement("select * from passageiro p1 "
					  		+ "inner join pessoa p2 "
					  		+ "on p1.codigo_pessoa = p2.codigo "
					  		+ "where p1.codigo = "+id);
			ResultSet rs = ps.executeQuery();
			
			Passageiro passageiro = new Passageiro();
			
			if(rs.next()){
				Pessoa p = new Pessoa(rs.getString("nome"),rs.getString("cpf"),
						rs.getString("cnpj"),rs.getString("endereco"),
						rs.getDate("data_nascimeto"),
						pessoa.estadoCivilDescricao(rs.getString("estado_civil")), rs.getBoolean("removido"));
				passageiro.setId(rs.getLong("codigo"));
				
				passageiro.setPessoa(p);
			}
			
			return passageiro;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	

	

}
