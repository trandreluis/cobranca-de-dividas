package edu.ifpb.monteiro.ads.controller;

import java.util.ArrayList;

import edu.ifpb.monteiro.ads.dao.DevedorDao;
import edu.ifpb.monteiro.ads.model.Devedor;
import edu.ifpb.monteiro.ads.view.DevedorCadastroJanela;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * 
 * @author Andre Luis
 * 
 *         e-mail: tr.andreluis@gmail.com
 *
 */

public class JanelaPrincipalController {

	@FXML
	private AnchorPane root;

	@FXML
	private TableView<Devedor> tabelaDevedores;

	@FXML
	private TableColumn<Devedor, String> colunaNomeDevedor;

	@FXML
	private TableColumn<Devedor, String> colunaCidadeDevedor;

	@FXML
	private TableColumn<Devedor, Integer> colunaIdadeDevedor;

	@FXML
	private TableColumn<Devedor, Double> colunaDividaDevedor;

	@FXML
	private Button botaoNovoDevedor;

	@FXML
	private Button botaoEditarDevedor;

	@FXML
	private Button botaoExcluirDevedor;

	@FXML
	private Button botaoNegociar;

	@FXML
	public void initialize() {

		ObservableList<Devedor> devedoresObservableList = FXCollections.observableArrayList();

		DevedorDao dao = new DevedorDao();

		ArrayList<Devedor> devedoresList = dao.buscarTodos();

		for (Devedor d : devedoresList) {
			devedoresObservableList.add(d);
		}

		colunaNomeDevedor.setCellValueFactory(valorDaCelula -> valorDaCelula.getValue().nome());

		tabelaDevedores.setItems(devedoresObservableList);

	}

	public void novoDevedor() {

		Alert alerta = new Alert(AlertType.INFORMATION);

		alerta.setTitle("Informacao");
		alerta.setHeaderText("Sucesso!");
		alerta.setContentText("Cadastrar novo devedor!");

		alerta.showAndWait();

		DevedorCadastroJanela devedorCadastro = new DevedorCadastroJanela(root);
		devedorCadastro.show();

	}

	public void editarDevedor() {

		Alert alerta = new Alert(AlertType.INFORMATION);

		alerta.setTitle("Informacao");
		alerta.setHeaderText("Sucesso!");
		alerta.setContentText("Editar devedor!");

		alerta.showAndWait();

	}

	public void excluirDevedor() {

		Alert alerta = new Alert(AlertType.INFORMATION);

		alerta.setTitle("Informacao");
		alerta.setHeaderText("Sucesso!");
		alerta.setContentText("Excluir devedor!");

		alerta.showAndWait();

	}

	public void negociarComDevedor() {

		Alert alerta = new Alert(AlertType.INFORMATION);

		alerta.setTitle("Informacao");
		alerta.setHeaderText("Sucesso!");
		alerta.setContentText("Negociar com devedor!");

		alerta.showAndWait();

	}

}
