package br.com.voo.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.voo.model.Passageiro;
import br.com.voo.model.Pessoa;
import br.com.voo.util.FactoryConexao;
import br.com.voo.util.ValidarPessoa;

public class PassageiroDAO {

	private Connection conexao;
	private PessoaDAO pessoaDAO;
	private ContatoDAO contato;

	public PassageiroDAO() {
		conexao = FactoryConexao.getConnection();
		pessoaDAO = new PessoaDAO();
		contato = new ContatoDAO();
	}

	public boolean alterar(Passageiro _passageiro) throws Exception {

		try {
			conexao.setAutoCommit(false);
			if (pessoaDAO.alterar(_passageiro.getPessoa(), conexao)) {

				PreparedStatement ps = conexao.prepareStatement("UPDATE public.passageiro "
						+ "set removido=? WHERE codigo = " + _passageiro.getId());

				ps.setBoolean(1, _passageiro.isRemovido());
				ps.executeUpdate();
				conexao.commit();
				
				return true;
			} else
				conexao.rollback();
			return false;
		} catch (Exception e) {
			conexao.rollback();
			throw new Exception(e.getMessage());
		}
	}

	private Passageiro validaSeResponsavelNulo(Passageiro responsavel) {
		return responsavel != null ? responsavel : new Passageiro();
	}

	public boolean inserir(Passageiro _passageiro,  boolean salvarPessoa) throws Exception {

		try {
			PreparedStatement ps = conexao.prepareStatement(
					"INSERT INTO public.passageiro(codigo_pessoa, removido)" + "VALUES (?, ?) RETURNING codigo;");

			conexao.setAutoCommit(false);

			if (pessoaDAO.salvar(_passageiro.getPessoa(), conexao, salvarPessoa)) {
				long codigoPessoa = pessoaDAO
						.consultar(_passageiro.getPessoa().getCpf(), _passageiro.getPessoa().getCnpj(), conexao)
						.getId();

				ps.setLong(1, codigoPessoa);
				ps.setBoolean(2, _passageiro.isRemovido());
				ResultSet rs = ps.executeQuery();
				rs.next();
				_passageiro.setId(rs.getLong("codigo"));
				//contato.ineserir(_passageiro.getContato(), conexao);
				conexao.commit();
				return true;

			} else
				conexao.rollback();

			return false;

		} catch (Exception e) {
			conexao.rollback();
			throw new Exception(e.getMessage());
		}

	}

	public List<Passageiro> listar(String nome) throws Exception {

		try {
			String sql = "select * from passageiro p1 inner join pessoa p2 "
					+ "on p1.codigo_pessoa = p2.codigo where p2.nome like '%" + nome + "%' and p1.removido != true";

			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Passageiro> lista = new ArrayList<Passageiro>();

			while (rs.next()) {
				Passageiro passageiro = new Passageiro();
				Pessoa p = new Pessoa(rs.getString("nome"), rs.getString("cpf"), rs.getString("cnpj"),
						rs.getString("endereco"), rs.getDate("data_nascimento"),
						ValidarPessoa.estadoCivilDescricao(rs.getString("estado_civil")), rs.getString("telefone"),
						rs.getString("email"), rs.getString("senha"));
				passageiro.setId(rs.getLong("codigo"));
				passageiro.setPessoa(p);
				lista.add(passageiro);
			}

			return lista;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
	
	public boolean excluir(long id) throws Exception {
		try {
			Passageiro passageiro = consultar(id);

			passageiro.setRemovido(true);

			return alterar(passageiro);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public Passageiro consultar(Long id) throws Exception {
		try {
			PreparedStatement ps = conexao.prepareStatement("select * from passageiro p1 " + "inner join pessoa p2 "
					+ "on p1.codigo_pessoa = p2.codigo where p1.codigo = " +id+" and p1.removido != true");
			ResultSet rs = ps.executeQuery();

			Passageiro passageiro = new Passageiro();

			if (rs.next()) {
				Pessoa p = new Pessoa(rs.getLong("codigo_pessoa"), rs.getString("nome"), rs.getString("cpf"), rs.getString("cnpj"),
						rs.getString("endereco"), rs.getDate("data_nascimento"),
						ValidarPessoa.estadoCivilDescricao(rs.getString("estado_civil")), rs.getString("telefone"),
						rs.getString("email"), rs.getString("senha"));
				passageiro.setId(rs.getLong("codigo"));

				passageiro.setPessoa(p);
			}

			return passageiro;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public Passageiro consultarPeloIdPessoa(Long id) throws Exception {
		try {
			PreparedStatement ps = conexao.prepareStatement("select * from passageiro p1 " + "inner join pessoa p2 "
					+ "on p1.codigo_pessoa = p2.codigo where p1.codigo_pessoa = " +id+" and p1.removido != true");
			ResultSet rs = ps.executeQuery();

			Passageiro passageiro = new Passageiro();

			if (rs.next()) {
				Pessoa p = new Pessoa(rs.getLong("codigo_pessoa"), rs.getString("nome"), rs.getString("cpf"), rs.getString("cnpj"),
						rs.getString("endereco"), rs.getDate("data_nascimento"),
						ValidarPessoa.estadoCivilDescricao(rs.getString("estado_civil")), rs.getString("telefone"),
						rs.getString("email"), rs.getString("senha"));
				passageiro.setId(rs.getLong("codigo"));

				passageiro.setPessoa(p);
			}

			return passageiro;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public Passageiro buscar(String cpf) throws Exception {
		try {
			PreparedStatement ps = conexao.prepareStatement("select p2.codigo as codigoPassageiro,* from pessoa p1 " + "left join passageiro p2 "
					+ "on p2.codigo_pessoa = p1.codigo where p1.cpf = '" + cpf + "'");
			ResultSet rs = ps.executeQuery();

			Passageiro passageiro = new Passageiro();

			if (rs.next()) {
				Pessoa p = new Pessoa(rs.getLong("codigo"),rs.getString("nome"), rs.getString("cpf"), rs.getString("cnpj"),
						rs.getString("endereco"), rs.getDate("data_nascimento"),
						ValidarPessoa.estadoCivilDescricao(rs.getString("estado_civil")), rs.getString("telefone"),
						rs.getString("email"), rs.getString("senha"));
				
				Long idPessoa = rs.getLong("codigoPassageiro");
				passageiro.setId(rs.getLong("codigoPassageiro"));

				passageiro.setPessoa(p);
			}

			return passageiro;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
