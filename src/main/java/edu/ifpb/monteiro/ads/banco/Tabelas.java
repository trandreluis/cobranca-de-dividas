package edu.ifpb.monteiro.ads.banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 
 * @author Andre Luis
 * 
 *         e-mail: tr.andreluis@gmail.com
 *
 */

public class Tabelas {

	private static Connection conexao;

	public Tabelas() {
		conexao = ConexaoDB.getConnection();
	}

	public void criarBanco() {
		criarTabelaDevedores();
		criarTabelaEnderencos();
		criarTabelaDividas();
		criarTabelaParcelas();
	}
	
	private void criarTabelaDevedores() {

		String sql = "CREATE TABLE devedores(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY"
				+ " (START WITH 1, INCREMENT BY 1), id_divida INTEGER NOT NULL,"
				+ " nome VARCHAR(70) NOT NULL, cpf VARCHAR(15) NOT NULL,"
				+ " data_nascimento DATE NOT NULL, id_endereco INTEGER NOT NULL)";

		try {
			PreparedStatement statement = conexao.prepareStatement(sql);

			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void criarTabelaEnderencos() {

		String sql = "CREATE TABLE enderecos(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY"
				+ " (START WITH 1, INCREMENT BY 1), rua VARCHAR(60) NOT NULL, numero VARCHAR(10) NOT NULL,"
				+ " bairro VARCHAR(60) NOT NULL, cidade VARCHAR(60) NOT NULL, estado VARCHAR(50) NOT NULL,"
				+ " ponto_referencia VARCHAR(50))";

		try {
			PreparedStatement statement = conexao.prepareStatement(sql);

			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void criarTabelaDividas() {

		String sql = "CREATE TABLE dividas(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY"
				+ " (START WITH 1, INCREMENT BY 1), valor DOUBLE NOT NULL, data_divida DATE NOT NULL,"
				+ " descricao VARCHAR(60) NOT NULL)";

		try {
			PreparedStatement statement = conexao.prepareStatement(sql);

			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void criarTabelaParcelas() {

		String sql = "CREATE TABLE parcelas(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY"
				+ " (START WITH 1, INCREMENT BY 1), id_divida INTEGER NOT NULL, valor_parcela DOUBLE NOT NULL,"
				+ "data_parcela DATE NOT NULL, paga BOOLEAN NOT NULL, atrasada BOOLEAN NOT NULL)";

		try {
			PreparedStatement statement = conexao.prepareStatement(sql);

			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
