package br.com.voo.dal;

import java.security.spec.PSSParameterSpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.SwingWorker;

import br.com.voo.model.Cnpj;
import br.com.voo.model.Cpf;
import br.com.voo.model.Documento;
import br.com.voo.model.EstadoCivil;
import br.com.voo.model.Pessoa;
import br.com.voo.util.FactoryConexao;
import br.com.voo.util.ValidarPessoa;

public class PessoaDAO {

	private Connection con;

	public PessoaDAO() {
		con = FactoryConexao.getConnection();
	}

	public boolean salvar(Pessoa _pessoa, Connection conexao) throws Exception {
		try {
			
			
			
			PreparedStatement ps = conexao
					.prepareStatement("INSERT INTO public.pessoa("
							+ "nome, cpf, cnpj, telefone, email, senha, endereco, data_nascimento, "
							+ "estado_civil, removido) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

		
			ps.setString(1, _pessoa.getNome());
			ps.setString(2, _pessoa.getCpf().getNumero());
			ps.setString(3, _pessoa.getCnpj().getNumero());
			ps.setString(4, _pessoa.getTelefone());
			ps.setString(5, _pessoa.getEmail());
			ps.setString(6, _pessoa.getSenha());
			ps.setString(7, _pessoa.getEndereco());
			ps.setDate(8, new java.sql.Date(_pessoa.getDataNascimento().getTime()));
			ps.setString(9, ValidarPessoa.estadoCivilDescricao(_pessoa.getEstadoCivil()));
			ps.setBoolean(10, _pessoa.isRemovido());
			
			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public boolean alterar(Pessoa _pessoa, Connection conexao) throws Exception {
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"UPDATE public.pessoa " + "SET nome=?, cpf=?, " + "cnpj=?, endereco=?, data_nascimento=?, "
							+ "estado_civil=?, removido=? ,telefone=?, email=? ,senha=?" + "WHERE codigo =" + _pessoa.getId());
			
			ps.setString(1, _pessoa.getNome());
			ps.setString(2, _pessoa.getCpf().getNumero());
			ps.setString(3, _pessoa.getCnpj().getNumero());
			ps.setString(4, _pessoa.getEndereco());
			ps.setDate(5, new java.sql.Date(_pessoa.getDataNascimento().getTime()));
			ps.setString(6, ValidarPessoa.estadoCivilDescricao(_pessoa.getEstadoCivil()));
			ps.setBoolean(7, _pessoa.isRemovido());
			ps.setString(8, _pessoa.getTelefone());
			ps.setString(9, _pessoa.getEmail());
			ps.setString(10, _pessoa.getSenha());

			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public Pessoa consultar(Cpf cpf, Cnpj cnpj, Connection conexao) throws Exception {

		this.con = conexao;

		Documento doc = cpf != null ? cpf : cnpj;
		return consultar(doc);

	}

	public Pessoa consultar(Documento doc) throws Exception {
		try {

			String documento = "";
			if (doc instanceof Cpf)
				documento = "cpf";
			else if (doc instanceof Cnpj)
				documento = "cnpj";

			PreparedStatement ps = con.prepareStatement(
					"select * from public.pessoa" + " where " + documento + " ='" + doc.getNumero() + "' and removido != true");

			ResultSet rs = ps.executeQuery();

			Pessoa pessoa = null;

			if (rs.next()) {
				pessoa = new Pessoa(rs.getLong("codigo"), rs.getString("nome"), rs.getString("cpf"),
						rs.getString("cnpj"), rs.getString("endereco"), rs.getDate("data_nascimento"),
						ValidarPessoa.estadoCivilDescricao(rs.getString("estado_civil")),
						rs.getString("telefone"), rs.getString("email"), rs.getString("senha"));
			}

			return pessoa != null ? pessoa : new Pessoa();

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
