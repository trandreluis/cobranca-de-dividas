package edu.ifpb.monteiro.ads.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ifpb.monteiro.ads.banco.ConexaoDB;
import edu.ifpb.monteiro.ads.model.DevedorAntigo;
import edu.ifpb.monteiro.ads.model.DevedorProperty;
import edu.ifpb.monteiro.ads.model.Divida;
import edu.ifpb.monteiro.ads.model.Endereco;

/**
 * 
 * @author Andre Luis
 * 
 *         e-mail: tr.andreluis@gmail.com
 *
 */

public class DevedorPropertyDao {

	private Connection conexao = ConexaoDB.getConnection();

	public static void main(String[] args) {

		Divida divida = new Divida(120, new Date(2014, 12, 9), "Sapato Social");
		Endereco endereco = new Endereco("Rua Alves Lucena", "33", "Vila da Cohab", "Arcoverde", "Pernambuco",
				"Proximo a saída pra Pesqueira");
		DevedorProperty devedor = new DevedorProperty(divida, "Andre", "426765326782", new Date(1980, 8, 7), endereco);

		DevedorPropertyDao dao = new DevedorPropertyDao();
//		sucesso no cadastro
		dao.salvar(devedor);
		
		ArrayList<DevedorProperty> devedores = dao.buscarTodos();
		
		for(DevedorProperty d : devedores) {
			System.out.println(d.toString());
		}

	}

	public void salvar(DevedorProperty devedor) {

		String sql = "INSERT INTO devedores(id_divida, nome, cpf, data_nascimento, id_endereco) VALUES (?, ?, ?, ?, ?)";

		try {

			PreparedStatement statement = conexao.prepareStatement(sql);

			statement.setLong(1, devedor.getDivida().getId());
			statement.setString(2, devedor.getNome());
			statement.setString(3, devedor.getNome());
			statement.setDate(4, Date.valueOf(devedor.getDataNascimento()));
			statement.setLong(5, devedor.getEndereco().getId());
			
			statement.execute();
			
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public DevedorAntigo buscar(long idDevedor) {
		return null;
	}

	public ArrayList<DevedorProperty> buscarTodos() {
		
		ArrayList<DevedorProperty> devedores = new ArrayList<>();
		
		String sql = "SELECT * FROM devedores";
		
		try {
			
			PreparedStatement statement = conexao.prepareStatement(sql);
			
			ResultSet result = statement.executeQuery();
			
			DevedorProperty devedor;
			
			while(result.next()) {
				
				devedor = new DevedorProperty();
				devedor.setNome(result.getString("nome"));
				devedor.setCpf(result.getString("cpf"));
				devedor.setDataNascimento(result.getDate("data_nascimento").toLocalDate());
				
				devedores.add(devedor);
			}
			
			statement.close();
			result.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return devedores;
	}

	public void apagar(long idDevedor) {

	}

}
