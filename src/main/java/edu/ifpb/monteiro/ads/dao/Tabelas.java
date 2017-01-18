package edu.ifpb.monteiro.ads.dao;

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

	private Connection conexao;

	public Tabelas() {
		conexao = ConexaoDB.getConnection();
	}

	public static void main(String[] args) {

		Tabelas t = new Tabelas();
		t.criarTabelaClientes();

	}

	public void criarTabelaClientes() {

		String sql = "CREATE TABLE clientes(" + "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY"
				+ " (START WITH 1, INCREMENT BY 1), id_divida LONG VARCHAR, nome VARCHAR(70) NOT NULL,"
				+ " cpf VARCHAR(15) NOT NULL, data_nascimento DATE, endereco LONG VARCHAR)";

		try {
			PreparedStatement statement = conexao.prepareStatement(sql);

			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void criarTabelaEnderenco() {

	}

	public void criarTabelaDivida() {

	}

}
