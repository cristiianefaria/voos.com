package br.com.voo.dal;

import java.security.spec.PSSParameterSpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.SwingWorker;

import br.com.voo.model.EstadoCivil;
import br.com.voo.model.Pessoa;

public class PessoaDAO {

	public boolean salvar(Pessoa _pessoa, Connection conexao) throws Exception {
		try {
			PreparedStatement ps = conexao
					.prepareStatement("INSERT INTO public.pessoa(" + "nome, cpf, cnpj, endereco, data_nascimeto, "
							+ "estado_civil, removido)" + "VALUES (?, ?, ?, ?, ?, ?, ?);");

			ps.setString(1, _pessoa.getNome());
			ps.setString(2, _pessoa.getCpf().getNumero());
			ps.setString(3, _pessoa.getCnpj().getNumero());
			ps.setString(4, _pessoa.getEndereco());
			ps.setDate(5, new java.sql.Date(_pessoa.getDataNascimento().getTime()));
			ps.setString(6, estadoCivilDescricao(_pessoa.getEstadoCivil()));

			ps.setBoolean(7, _pessoa.isRemovido());


			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}


	public String estadoCivilDescricao(EstadoCivil estadoCivil) {


		String descricao = "";
		switch (estadoCivil) {
		case Casado:
			descricao = "Casado";
			break;
		case Solteiro:
			descricao = "Solteito";
			break;
		case Divorciado:
			descricao = "Divorciado";
			break;
		case Viuvo:
			descricao = "Viuvo";
			break;
		default:
			break;
		}

		return descricao;

	}
	
	public EstadoCivil estadoCivilDescricao(String descricao) {

		EstadoCivil estadoCivil = EstadoCivil.Solteiro;
		switch (descricao) {
		case "Casado":
			estadoCivil = EstadoCivil.Casado;
			break;
		case "Divorciado":
			estadoCivil = EstadoCivil.Divorciado;
			break;
		case "Viuvo":
			estadoCivil = EstadoCivil.Viuvo;
			break;
		default:
			break;
		}

		return estadoCivil;

	}

	public List<Pessoa> buscar(Pessoa pessoa) {

		return null;
	}

	public boolean exluir(int codigo) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean Alterar(Pessoa _pessoa,Connection conexao) throws Exception {
		try {
			PreparedStatement ps = conexao
					.prepareStatement("UPDATE public.pessoa "
							+ "SET nome=?, cpf=?, "
							+ "cnpj=?, endereco=?, data_nascimeto=?, "
							+ "estado_civil=?, removido=? "
							+ "WHERE codigo ="+_pessoa.getId());
			ps.setString(1, _pessoa.getNome());
			ps.setString(2, _pessoa.getCpf().getNumero());
			ps.setString(3, _pessoa.getCnpj().getNumero());
			ps.setString(4, _pessoa.getEndereco());
			ps.setDate(5, new java.sql.Date(_pessoa.getDataNascimento().getTime()));
			ps.setString(6, estadoCivilDescricao(_pessoa.getEstadoCivil()));
			ps.setBoolean(7, _pessoa.isRemovido());
			
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public Pessoa consultar(String cpf, String cnpj, Connection conexao) throws Exception {
		try {
			PreparedStatement preparedStatement;
			if (!cpf.isEmpty()) {
				 preparedStatement = conexao
						.prepareStatement("select * from public.pessoa where cpf ='"+cpf+"'");
			} else {
				 preparedStatement = conexao
						.prepareStatement("select * from public.pessoa where cpf ='"+cnpj+"'");
			}
			
			ResultSet rs = preparedStatement.executeQuery();
			
			Pessoa pessoa = new Pessoa();
			if(rs.next()){
				pessoa.setId(rs.getLong("codigo"));
				
			}

			return pessoa;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

}
