package edu.ifpb.monteiro.ads.view;

import java.io.IOException;

import edu.ifpb.monteiro.ads.controller.JanelaParcelaController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * @author Andre Luis
 * 
 *         e-mail: tr.andreluis@gmail.com
 *
 */

public class JanelaParcela extends Stage {

	AnchorPane root;
	private static FXMLLoader fxml;

	public JanelaParcela(Pane pai) {

		setResizable(false);
		setTitle("Janela de Parcelas");

		// Define a janela principal como dona desta
		initOwner(pai.getScene().getWindow());

		// Define o icone
		getIcons().add(new Image("icone/cedula.png"));

		// Define esta janela como modal, nao permitindo que se mexa na
		// principal enquanto esta nao for finalizada
		initModality(Modality.WINDOW_MODAL);

		try {

			fxml = new FXMLLoader(getClass().getResource("/fxml/JanelaParcela.fxml"));
			root = (AnchorPane) fxml.load();

		} catch (IOException e) {
			e.printStackTrace();
		}

		Scene cena = new Scene(root);
		setScene(cena);

	}

	public static JanelaParcelaController getController() {

		JanelaParcelaController controller = (JanelaParcelaController) fxml.getController();

		return controller;
	}

}
