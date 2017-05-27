package br.com.voo.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.voo.model.Cliente;
import br.com.voo.model.Itinerario;
import br.com.voo.util.FactoryConexao;

public class ItinerarioDAO {

	private Connection connection;

	public ItinerarioDAO() {
		connection = FactoryConexao.getConnection();
	}

	public boolean incluir(Itinerario objeto) throws SQLException {

		PreparedStatement ps = connection
				.prepareStatement("INSERT INTO itinerario (origem, destino, valor, removido)" + "VALUES (?, ?, ?, ?);");
		ps.setString(1, objeto.getOrigem());
		ps.setString(2, objeto.getDestino());
		ps.setDouble(3, objeto.getValor());
		ps.setBoolean(4, objeto.isRemovido());
		ps.execute();

		ps.close();

		return true;
	}

	public boolean alterar(Itinerario itinerario) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(
				"update itinerario set origem=?, destino=?, valor=?, removido=? " + " where codigo=?");
		ps.setString(1, itinerario.getOrigem());
		ps.setString(2, itinerario.getDestino());
		ps.setDouble(3, itinerario.getValor());
		ps.setBoolean(4, itinerario.isRemovido());
		ps.setLong(5, itinerario.getId());
		ps.executeUpdate();

		ps.close();

		return true;
	}

	public List<Itinerario> listar() throws SQLException {
		List<Itinerario> lista = new ArrayList<Itinerario>();

		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery("select * from itinerario");

		while (rs.next()) {
			Itinerario itinerario = new Itinerario();
			itinerario.setId(rs.getLong("codigo"));
			itinerario.setOrigem(rs.getString("origem"));
			itinerario.setDestino(rs.getString("destino"));
			itinerario.setValor(rs.getDouble("valor"));
			itinerario.setRemovido(rs.getBoolean("removido"));
			lista.add(itinerario);
		}

		st.close();
		return lista;
	}

	public Itinerario consultar(Long codigo) throws SQLException {
		Itinerario itinerario = new Itinerario();
		PreparedStatement ps = connection.prepareStatement("select * from itinerario where codigo=? and removido = false");
		ps.setLong(1, codigo);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			itinerario.setId(rs.getLong("codigo"));
			itinerario.setOrigem(rs.getString("origem"));
			itinerario.setDestino(rs.getString("destino"));
			itinerario.setValor(rs.getDouble("valor"));
			itinerario.setRemovido(rs.getBoolean("removido"));
		}
		ps.close();
		return itinerario;
	}

	public boolean remover(Long id) throws Exception {
		try {
			Itinerario itinerario = consultar(id);
			itinerario.setRemovido(true);

			return alterar(itinerario);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
