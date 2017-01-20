package edu.ifpb.monteiro.ads.controller;

import edu.ifpb.monteiro.ads.model.Devedor;
import edu.ifpb.monteiro.ads.view.DevedorCadastroJanela;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * 
 * @author Andre Luis
 * 
 *         e-mail: tr.andreluis@gmail.com
 *
 */

public class JanelaPrincipalController {

	@FXML
	AnchorPane root;
	
	@FXML
	TableView<Devedor> tabelaDevedores;
	
	@FXML
	TableColumn<String, Devedor> colunaNomeDevedor;
	
	@FXML
	TableColumn<String, Devedor> colunaCidadeDevedor;
	
	@FXML
	TableColumn<Integer, Devedor> colunaIdadeDevedor;
	
	@FXML
	TableColumn<Double, Devedor> colunaDividaDevedor;

	@FXML
	Button botaoNovoDevedor;
	
	@FXML
	Button botaoEditarDevedor;
	
	@FXML
	Button botaoExcluirDevedor;
	
	@FXML
	Button botaoNegociar;
	
	
	public void novoDevedor() {

		Alert alerta = new Alert(AlertType.INFORMATION);

		alerta.setTitle("Informacao");
		alerta.setHeaderText("Sucesso!");
		alerta.setContentText(
				"Cadastrar novo devedor!");

		alerta.showAndWait();
		
		DevedorCadastroJanela devedorCadastro = new DevedorCadastroJanela(root);
		devedorCadastro.show();

	}
	
	public void editarDevedor() {

		Alert alerta = new Alert(AlertType.INFORMATION);

		alerta.setTitle("Informacao");
		alerta.setHeaderText("Sucesso!");
		alerta.setContentText(
				"Editar devedor!");

		alerta.showAndWait();

	}

	public void excluirDevedor() {

		Alert alerta = new Alert(AlertType.INFORMATION);

		alerta.setTitle("Informacao");
		alerta.setHeaderText("Sucesso!");
		alerta.setContentText(
				"Excluir devedor!");

		alerta.showAndWait();

	}
	
	public void negociarComDevedor() {

		Alert alerta = new Alert(AlertType.INFORMATION);

		alerta.setTitle("Informacao");
		alerta.setHeaderText("Sucesso!");
		alerta.setContentText(
				"Negociar com devedor!");

		alerta.showAndWait();

	}
	
}
