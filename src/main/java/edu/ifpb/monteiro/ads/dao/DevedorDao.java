package edu.ifpb.monteiro.ads.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import edu.ifpb.monteiro.ads.banco.ConexaoDB;
import edu.ifpb.monteiro.ads.model.Devedor;
import edu.ifpb.monteiro.ads.model.Divida;
import edu.ifpb.monteiro.ads.model.Endereco;

/**
 * 
 * @author Andre Luis
 * 
 *         e-mail: tr.andreluis@gmail.com
 *
 */

public class DevedorDao {

	private Connection conexao = ConexaoDB.getConnection();

	public static void main(String[] args) {

		Divida divida = new Divida(120, LocalDate.of(2014, 12, 9), "Sapato Social");

		DividaDao daoDivida = new DividaDao();
		
		int idGeradoDivida = daoDivida.salvar(divida);
		
		Divida dividaRecuperadaDoBanco = daoDivida.buscarPorID(idGeradoDivida);
		
		Endereco endereco = new Endereco("Rua Alves Lucena", "33", "Vila da Cohab", "Arcoverde", "Pernambuco",
				"Proximo a saida pra Pesqueira");

		EnderecoDao daoEndereco = new EnderecoDao();
		
		int idGeradoEndereco = daoEndereco.salvar(endereco);
		
		Endereco enderecoRecuperadoDoBanco = daoEndereco.buscarPorID(idGeradoEndereco);
		
		Devedor devedor = new Devedor(dividaRecuperadaDoBanco, "Andre", "426765326782", LocalDate.of(2014, 12, 9), enderecoRecuperadoDoBanco);

		DevedorDao daoDevedor = new DevedorDao();

		daoDevedor.salvar(devedor);

		ArrayList<Devedor> devedores = daoDevedor.buscarTodos();

		System.out.println("------ TODOS OS CADASTRADOS ------");
		for (Devedor d : devedores) {
			System.out.println(d);
		}
		System.out.println("------ FIM ------");

		System.out.println("------ DEVEDOR CADASTRADO COM ID: 201 ------");
		System.out.println(daoDevedor.buscarPorID(201));
		
		
	}

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
			statement.setString(1, "%"+nomeDevedor+"%");

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

	/**
	 * Falta montar o devedor com os atributos completos (endereco)
	 * @return
	 */
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

	public void apagar(long idDevedor) {

	}

}
