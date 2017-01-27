package edu.ifpb.monteiro.ads.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import edu.ifpb.monteiro.ads.banco.ConexaoDB;
import edu.ifpb.monteiro.ads.model.Divida;
import edu.ifpb.monteiro.ads.model.Parcela;

/**
 * 
 * @author Andre Luis
 * 
 *         e-mail: tr.andreluis@gmail.com
 *
 */

public class ParcelaDao {

	private Connection conexao = ConexaoDB.getConnection();


	public Integer salvar(Parcela parcela) {

		String sql = "INSERT INTO dividas(valor, data_divida, descricao) VALUES (?,?,?)";

		try {

			// Instanciando o PreparedStatement solicitando o retorno das chaves
			// (ID) geradas.
			PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			statement.setDouble(1, parcela.getValor());

			statement.execute();

			// Obtendo ResultSet com as chaves geradas.
			ResultSet keys = statement.getGeneratedKeys();

			// Se alguma chave tiver sido criada com o execute() anterior
			if (keys.next()) {
				return keys.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	public Parcela buscarPorID(Integer idParcela) {

		String sql = "SELECT * FROM dividas WHERE id = (?)";

		Parcela parcela = null;

		try {

			PreparedStatement statement = conexao.prepareStatement(sql);

			statement.setInt(1, idParcela);

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				parcela = new Parcela();
				parcela.setId(result.getInt("id"));
				parcela.setValor(result.getDouble("valor"));

			}

			statement.close();
			result.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return parcela;

	}

	public ArrayList<Parcela> buscarTodos() {

		ArrayList<Parcela> dividas = new ArrayList<>();

		String sql = "SELECT * FROM dividas";

		try {

			PreparedStatement statement = conexao.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			Parcela parcela;

			while (result.next()) {

				parcela = new Parcela();
				parcela.setId(result.getInt("id"));
				parcela.setValor(result.getDouble("valor"));

				dividas.add(parcela);

			}

			statement.close();
			result.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dividas;

	}

	public void atualizar(Parcela parcela) {

		String sql = "UPDATE dividas SET valor = ?, data_divida = ?, descricao = ? WHERE id = (?)";

		try {

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setDouble(1, parcela.getValor());

			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void excluir(Integer idParcela) {

		String sql = "DELETE FROM dividas WHERE id = (?)";

		try {

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, idParcela);

			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
