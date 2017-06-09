package br.com.voo.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.voo.model.Cliente;
import br.com.voo.model.Pessoa;
import br.com.voo.model.TipoCliente;
import br.com.voo.util.FactoryConexao;
import br.com.voo.util.ValidarPessoa;

public class ClienteDAO {

	private Connection conexao;
	private PessoaDAO pessoa;

	public ClienteDAO() {
		conexao = FactoryConexao.getConnection();
		pessoa = new PessoaDAO();
	}

	public boolean inserir(Cliente _cliente) throws Exception {
		try {

			PreparedStatement ps = conexao.prepareStatement("INSERT INTO public.cliente " + "(codigo_pessoa, milhagem, "
					+ "percent_desconto, tipo_cliente, removido) " + "VALUES (?, ?, ?, ?, ?);");
			conexao.setAutoCommit(false);
			if (pessoa.salvar(_cliente.getPessoa(), conexao)) {
				long codigoPessoa = pessoa
						.consultar(_cliente.getPessoa().getCpf(), _cliente.getPessoa().getCnpj(), conexao).getId();

				ps.setLong(1, codigoPessoa);
				ps.setDouble(2, _cliente.getMilhagem());
				ps.setDouble(4, _cliente.getPercentDesconto());
				ps.setString(5, obterTipoClienteDescricao(_cliente.getTipoCliente()));
				ps.setBoolean(6, _cliente.isRemovido());

				ps.execute();
				conexao.commit();
				return true;
			} else

				return false;

		} catch (Exception e) {
			conexao.rollback();
			throw new Exception(e.getMessage());
		}
	}

	private String obterTipoClienteDescricao(TipoCliente tipoCliente) {

		String descricao = "";
		switch (tipoCliente) {
		case clienteFinal:
			descricao = "Cliente Final";
			break;
		case Parceiro:
			descricao = "Fornecedor";
		default:
			break;
		}
		return descricao;
	}

	public List<Cliente> listar(String nome) throws Exception {
		try {

			String sql = "select * from cliente c1 " + "inner join pessoa p1 " + "on c1.codigo_pessoa = p1.codigo "
					+ "where p1.nome like '%" + nome + "%' and p1.removido != true";

			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Cliente cliente = null;
			List<Cliente> lista = new ArrayList<Cliente>();
			while (rs.next()) {

				Pessoa p = new Pessoa(rs.getString("nome"), rs.getString("cpf"), rs.getString("cnpj"),
						rs.getString("endereco"), rs.getDate("data_nascimeto"),
						ValidarPessoa.estadoCivilDescricao(rs.getString("estado_civil")), rs.getString("telefone"),
						rs.getString("email"), rs.getString("senha"));

				cliente = new Cliente(p);
				cliente = new Cliente(p);
				cliente.setId(rs.getLong("codigo"));
				cliente.setMilhagem(rs.getInt("milhagem"));
				cliente.setPercentDesconto(rs.getDouble("percent_desconto"));
				cliente.setTipoCliente(obterTipoCliente(rs.getString("tipo_cliente")));
				cliente.setRemovido(rs.getBoolean("removido"));
				lista.add(cliente);

			}

			return lista;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public Cliente consultar(Long id) throws Exception {
		try {
			PreparedStatement ps = conexao.prepareStatement("select * from cliente c1 " + "inner join pessoa p1 "
					+ "on c1.codigo_pessoa = p1.codigo " + "where c1.codigo = " +id+" and p1.removido != true");
			ResultSet rs = ps.executeQuery();

			Cliente cliente = null;
			if (rs.next()) {
				Pessoa p = new Pessoa(rs.getLong("codigo_pessoa"), rs.getString("nome"), rs.getString("cpf"), rs.getString("cnpj"),
						rs.getString("endereco"), rs.getDate("data_nascimeto"),
						ValidarPessoa.estadoCivilDescricao(rs.getString("estado_civil")), rs.getString("telefone"),
						rs.getString("email"), rs.getString("senha"));

				cliente = new Cliente(p);
				cliente.setId(rs.getLong("codigo"));
				cliente.setMilhagem(rs.getInt("milhagem"));
				cliente.setPercentDesconto(rs.getDouble("percent_desconto"));
				cliente.setTipoCliente(obterTipoCliente(rs.getString("tipo_cliente")));
				cliente.setRemovido(rs.getBoolean("removido"));
			}

			return cliente != null ? cliente : new Cliente();

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public boolean alterar(Cliente _cliente) throws Exception {
		try {
			conexao.setAutoCommit(false);
			if (pessoa.alterar(_cliente.getPessoa(), conexao)) {
				PreparedStatement ps = conexao
						.prepareStatement("UPDATE public.cliente " + "SET milhagem=?, percent_desconto=?, "
								+ "tipo_cliente=?, removido = ?" + "WHERE codigo = " + _cliente.getId());

				ps.setInt(1, _cliente.getMilhagem());
				ps.setDouble(3, _cliente.getPercentDesconto());
				ps.setString(4, obterTipoClienteDescricao(_cliente.getTipoCliente()));
				ps.setBoolean(5, _cliente.isRemovido());

				ps.executeUpdate();
				conexao.commit();
				return true;
			} else

				return false;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private TipoCliente obterTipoCliente(String tipoCliente) {

		TipoCliente tipo = TipoCliente.clienteFinal;

		if (tipoCliente.equals("Fornecedor"))
			tipo = TipoCliente.Parceiro;

		return tipo;
	}

	public boolean exluir(Long id) throws Exception {
		try {
			Cliente cliente = consultar(id);
			cliente.getPessoa().setRemovido(true);
			cliente.setRemovido(true);

			return alterar(cliente);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
