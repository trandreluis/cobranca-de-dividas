package edu.ifpb.monteiro.ads.dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

		Divida divida = new Divida(120, new Date(2017, 1, 19), "Sapato Nike PowerShift");
		Endereco endereco = new Endereco("Rua Antao Alves", "11A", "Centro", "Sertania", "Pernambuco",
				"Proximo a igreja velha");
		Devedor devedor = new Devedor(divida, "Andre", "90909098754665", new Date(1996, 10, 25), endereco);
		
		
		DevedorDao dao = new DevedorDao();
		dao.salvar(devedor);

	}

	public void salvar(Devedor devedor) {

		String sql = "INSERT INTO devedores(id_divida, nome, cpf, data_nascimento, id_endereco) VALUES (?, ?, ?, ?, ?)";

		try {

			PreparedStatement statement = conexao.prepareStatement(sql);

			statement.setLong(1, devedor.getDivida().getId());
			statement.setString(2, devedor.getNome());
			statement.setString(3, devedor.getNome());
			statement.setDate(4, devedor.getDataNascimento());
			statement.setLong(5, devedor.getEndereco().getId());

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
