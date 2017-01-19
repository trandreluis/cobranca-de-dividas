package edu.ifpb.monteiro.ads.dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ifpb.monteiro.ads.banco.ConexaoDB;
import edu.ifpb.monteiro.ads.model.Devedor;

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
			
			statement.setLong(1, 100);
			statement.setString(2, "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	public Devedor buscar(long idDevedor) {
		return null;
	}

	public ArrayList<Devedor> buscarTodos() {
		return null;
	}

	public void apagar(long idDevedor) {

	}

}
