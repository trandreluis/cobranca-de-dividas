package edu.ifpb.monteiro.ads.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
	
	public void salvar(Divida divida) {

		String sql = "INSERT INTO dividas(valor, data_divida, descricao) VALUES (?,?,?)";
		
		try {
			
			PreparedStatement statement = conexao.prepareStatement(sql);
			
			statement.setDouble(1, 29.9);
			statement.setDate(2, new Date(2017, 4, 4));
			statement.setString(3, "Sapato massa");
			
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

	public Divida buscar(long idDivida) {
		return null;
	}

	public ArrayList<Divida> buscarTodos() {
		return null;
	}

	public void apagar(long idDivida) {

	}

}
