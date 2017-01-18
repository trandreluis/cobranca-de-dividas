package edu.ifpb.monteiro.ads.dao;

import java.sql.Connection;

/**
 * 
 * @author Andre Luis
 * 
 * e-mail: tr.andreluis@gmail.com
 *
 */

public class Tabelas {

	private Connection conexao;
	
	public Tabelas() {
		conexao = ConexaoDB.getConnection();
	}
	
	public void criarTabelaClientes() {
		
	}
	
	public void criarTabelaEnderenco() {
		
	}
	
	public void criarTabelaDivida() {
		
	}
	
}
