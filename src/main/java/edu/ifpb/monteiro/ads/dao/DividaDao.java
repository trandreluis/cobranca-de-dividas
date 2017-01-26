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

/**
 * 
 * @author Andre Luis
 * 
 *         e-mail: tr.andreluis@gmail.com
 *
 */

public class DividaDao {

	private Connection conexao = ConexaoDB.getConnection();

	public static void main(String[] args) {

		LocalDate hoje = LocalDate.of(2017, 04, 04);

		Divida divida = new Divida(98.3, hoje, "Life Saco de pancada");

		DividaDao dao = new DividaDao();

		System.out.println("Obtendo ID");
		dao.salvar(divida);
		System.out.println("FIM");

		ArrayList<Divida> dividas = dao.buscarTodos();

		System.out.println("------ TODOS OS CADASTRADOS ------");

		for (Divida d : dividas) {
			System.out.println(d);
		}

		System.out.println("------ BUSCADO DIVIDA COM ID: 401 ------");
		System.out.println(dao.buscarPorID(401));
		
		System.out.println("EXCLUSAO DA DIDIVA COM ID: 2002");
		dao.excluir(2002);
		
		System.out.println("ALTERANDO DIVIDA COM ID: 701");
		
		Divida dividaSemAlteracoes = dao.buscarPorID(701);
		
		dividaSemAlteracoes.setDataDivida(LocalDate.now());
		dividaSemAlteracoes.setDescricao("CD");
		dividaSemAlteracoes.setValor(3.5);
		
		Divida dividaAlterada = dividaSemAlteracoes;
		
		dao.atualizar(dividaAlterada);
		
		ArrayList<Divida> dividasAtual = dao.buscarTodos();

		System.out.println("------ TODOS OS CADASTRADOS ------");

		for (Divida d : dividasAtual) {
			System.out.println(d);
		}

	}

	public Integer salvar(Divida divida) {

		String sql = "INSERT INTO dividas(valor, data_divida, descricao) VALUES (?,?,?)";

		try {

			// Instanciando o PreparedStatement solicitando o retorno das chaves
			// (ID) geradas.
			PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			statement.setDouble(1, divida.getValor());
			statement.setDate(2, Date.valueOf(divida.getDataDivida()));
			statement.setString(3, divida.getDescricao());

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

	public Divida buscarPorID(Integer idDivida) {

		String sql = "SELECT * FROM dividas WHERE id = (?)";

		Divida divida = null;

		try {

			PreparedStatement statement = conexao.prepareStatement(sql);

			statement.setInt(1, idDivida);

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				divida = new Divida();
				divida.setId(result.getInt("id"));
				divida.setValor(result.getDouble("valor"));
				divida.setDataDivida(result.getDate("data_divida").toLocalDate());
				divida.setDescricao(result.getString("descricao"));

			}

			statement.close();
			result.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return divida;

	}

	public ArrayList<Divida> buscarTodos() {

		ArrayList<Divida> dividas = new ArrayList<Divida>();

		String sql = "SELECT * FROM dividas";

		try {

			PreparedStatement statement = conexao.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			Divida divida;

			while (result.next()) {

				divida = new Divida();
				divida.setId(result.getInt("id"));
				divida.setValor(result.getDouble("valor"));
				divida.setDataDivida(result.getDate("data_divida").toLocalDate());
				divida.setDescricao(result.getString("descricao"));

				dividas.add(divida);

			}

			statement.close();
			result.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dividas;

	}

	public void atualizar(Divida divida) {

		String sql = "UPDATE dividas SET valor = ?, data_divida = ?, descricao = ? WHERE id = (?)";

		try {

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setDouble(1, divida.getValor());
			statement.setDate(2, Date.valueOf(divida.getDataDivida()));
			statement.setString(3, divida.getDescricao());
			statement.setInt(4, divida.getId());

			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void excluir(Integer idDivida) {

		String sql = "DELETE FROM dividas WHERE id = (?)";

		try {

			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, idDivida);

			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
