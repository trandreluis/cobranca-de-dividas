package edu.ifpb.monteiro.ads.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 
 * @author Andre Luis
 * 
 *         e-mail: tr.andreluis@gmail.com
 *
 */

public class DevedorCadastroController {

	@FXML
	private AnchorPane root;
	
	@FXML
	private TextField fieldNome;
	
	@FXML
	private TextField fieldCpf;
	
	@FXML
	private TextField fieldRua;
	
	@FXML
	private TextField fieldNumero;
	
	@FXML
	private TextField fieldBairro;
	
	@FXML
	private TextField fieldEstado;
	
	@FXML
	private TextField fieldCidade;
	
	@FXML
	private TextField fieldPontoReferencia;
	
	@FXML
	private TextField fieldDescricaoDivida;
	
	@FXML
	private TextField fieldValorDivida;
	
	@FXML
	private DatePicker dateDivida;
	
	@FXML
	private DatePicker dateNascimento;
	
	@FXML
	private Button botaoCadastrar;
	
	@FXML
	private Button botaoCancelar;
	
	@FXML
	public void initialize() {
		
	}
	
	@FXML
	public void cadastrarDevedor() {
		
		Alert alerta = new Alert(AlertType.INFORMATION);

		alerta.setTitle("Informacao");
		alerta.setHeaderText("Sucesso!");
		alerta.setContentText("Cadastrar devedor!");

		alerta.showAndWait();
		
	}
	
	@FXML
	public void cancelarCadastro() {
		fecharStage();
	}
	
	@FXML
	public void fecharStage() {
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}
	
}
