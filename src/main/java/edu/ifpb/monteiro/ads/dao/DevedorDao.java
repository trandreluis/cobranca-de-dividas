package edu.ifpb.monteiro.ads.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ifpb.monteiro.ads.banco.ConexaoDB;
import edu.ifpb.monteiro.ads.model.Devedor;
import edu.ifpb.monteiro.ads.model.Divida;
import edu.ifpb.monteiro.ads.model.Endereco;
import edu.ifpb.monteiro.ads.model.Parcela;

/**
 * 
 * @author Andre Luis
 * 
 *         e-mail: tr.andreluis@gmail.com
 *
 */

public class DevedorDao {

	private Connection conexao = ConexaoDB.getConnection();

	public void salvar(Devedor devedor) {

		String sql = "INSERT INTO devedores(id_divida, nome, cpf, data_nascimento, id_endereco) VALUES (?, ?, ?, ?, ?)";

		try {

			PreparedStatement statement = conexao.prepareStatement(sql);

			statement.setInt(1, devedor.getDivida().getId());
			statement.setString(2, devedor.getNome());
			statement.setString(3, devedor.getCpf());
			statement.setDate(4, Date.valueOf(devedor.getDataNascimento()));
			statement.setInt(5, devedor.getEndereco().getId());

			statement.execute();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Falta montar o devedor com os atributos completos (endereco)
	 * 
	 * @return
	 */

	public Devedor buscarPorID(Integer idDevedor) {

		DividaDao daoDivida = new DividaDao();
		EnderecoDao daoEndereco = new EnderecoDao();

		String sql = "SELECT * FROM devedores WHERE id = (?)";

		Devedor devedor = null;

		try {

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, idDevedor);

			ResultSet result = statement.executeQuery();

			Divida divida;
			Endereco endereco;

			while (result.next()) {

				devedor = new Devedor();
				devedor.setId(result.getInt("id"));

				divida = daoDivida.buscarPorID(result.getInt("id_divida"));

				devedor.setDivida(divida);
				devedor.setNome(result.getString("nome"));
				devedor.setCpf(result.getString("cpf"));
				devedor.setDataNascimento(result.getDate("data_nascimento").toLocalDate());

				endereco = daoEndereco.buscarPorID(result.getInt("id_endereco"));

				devedor.setEndereco(endereco);

			}

			statement.close();
			result.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return devedor;

	}

	public ArrayList<Devedor> buscarPorNome(String nomeDevedor) {

		ArrayList<Devedor> devedores = new ArrayList<>();

		DividaDao daoDivida = new DividaDao();
		EnderecoDao daoEndereco = new EnderecoDao();

		String sql = "SELECT * FROM devedores WHERE UPPER(nome) LIKE UPPER(?)";

		Devedor devedor = null;

		try {

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, "%" + nomeDevedor + "%");

			ResultSet result = statement.executeQuery();

			Divida divida;
			Endereco endereco;

			while (result.next()) {

				devedor = new Devedor();
				devedor.setId(result.getInt("id"));

				divida = daoDivida.buscarPorID(result.getInt("id_divida"));

				devedor.setDivida(divida);
				devedor.setNome(result.getString("nome"));
				devedor.setCpf(result.getString("cpf"));
				devedor.setDataNascimento(result.getDate("data_nascimento").toLocalDate());

				endereco = daoEndereco.buscarPorID(result.getInt("id_endereco"));

				devedor.setEndereco(endereco);

				devedores.add(devedor);

			}

			statement.close();
			result.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return devedores;

	}

	public ArrayList<Devedor> buscarTodos() {

		ArrayList<Devedor> devedores = new ArrayList<Devedor>();
		DividaDao daoDivida = new DividaDao();
		EnderecoDao daoEndereco = new EnderecoDao();

		String sql = "SELECT * FROM devedores";

		try {

			PreparedStatement statement = conexao.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			Devedor devedor;
			Divida divida;
			Endereco endereco;

			while (result.next()) {

				devedor = new Devedor();
				devedor.setId(result.getInt("id"));

				divida = daoDivida.buscarPorID(result.getInt("id_divida"));

				devedor.setDivida(divida);
				devedor.setNome(result.getString("nome"));
				devedor.setCpf(result.getString("cpf"));
				devedor.setDataNascimento(result.getDate("data_nascimento").toLocalDate());

				endereco = daoEndereco.buscarPorID(result.getInt("id_endereco"));

				devedor.setEndereco(endereco);

				devedores.add(devedor);

			}

			statement.close();
			result.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return devedores;
	}

	public boolean negociouDivida(Integer idDivida) {

		ParcelaDao daoParcela = new ParcelaDao();

		ArrayList<Parcela> parcelas = daoParcela.buscarPorDivida(idDivida);

		if (parcelas.size() == 0) {
			return false;
		}

		return true;

	}

	public void atualizar(Devedor devedor) {

		String sql = "UPDATE devedores SET nome = ?, cpf = ?, data_nascimento = ? WHERE id = (?)";

		try {

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, devedor.getNome());
			statement.setString(2, devedor.getCpf());
			statement.setDate(3, Date.valueOf(devedor.getDataNascimento()));
			statement.setInt(4, devedor.getId());

			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void excluir(Integer idDevedor) {

		DividaDao daoDivida = new DividaDao();
		EnderecoDao daoEndereco = new EnderecoDao();
		ParcelaDao daoParcela = new ParcelaDao();

		String sql = "DELETE FROM devedores WHERE id = (?)";

		try {

			Devedor devedor = buscarPorID(idDevedor);

			daoParcela.excluirPorDivida(devedor.getDivida().getId());

			daoDivida.excluir(devedor.getDivida().getId());
			daoEndereco.excluir(devedor.getEndereco().getId());

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, idDevedor);

			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
