package edu.ifpb.monteiro.ads.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author Andre Luis
 * 
 * e-mail: tr.andreluis@gmail.com
 *
 */

public class ConexaoDB {

	private static String url = "jdbc:derby:banco-sas;create=true;user=derby;password=derby";
	private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	
	public static Connection getConnection() {
		
		Connection conexao = null;
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		try {
			conexao = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conexao;
		
	}
	
}
