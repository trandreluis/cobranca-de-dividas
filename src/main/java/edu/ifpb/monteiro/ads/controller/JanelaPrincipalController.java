package edu.ifpb.monteiro.ads.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

/**
 * 
 * @author Andre Luis
 * 
 *         e-mail: tr.andreluis@gmail.com
 *
 */

public class JanelaPrincipalController {

	@FXML
	Button botaoTeste;

	public void teste() {

		Alert alerta = new Alert(AlertType.INFORMATION);

		alerta.setTitle("Informacao");
		alerta.setHeaderText("Sucesso!");
		alerta.setContentText(
				"O seu botao esta funcionando corretamente!\nIsso significa dizer que o seu FXML esta apontando para o Controller de forma correta e so seu Controller, por sua vez, agiu de maneira esperada!");

		alerta.showAndWait();

	}

}
