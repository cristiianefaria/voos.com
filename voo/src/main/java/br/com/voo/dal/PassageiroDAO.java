package br.com.voo.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

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
			
			return false;
		} catch (Exception e) {
			conexao.rollback();
			throw new Exception(e.getMessage());
		}
	}

	private boolean inserir(Passageiro _passageiro) throws Exception {

		try {
			PreparedStatement ps = conexao.prepareStatement(
					"INSERT INTO public.passageiro(codigo_pessoa, responsavel, removido)" + "VALUES (?, ?, ?);");

			conexao.setAutoCommit(false);
			
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
			

		} catch (Exception e) {
			conexao.rollback();
			throw new Exception(e.getMessage());
		}

	}

	public List<Passageiro> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return true;
	}

}
