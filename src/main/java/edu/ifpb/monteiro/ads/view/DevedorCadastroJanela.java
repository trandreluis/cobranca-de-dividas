package edu.ifpb.monteiro.ads.view;

import java.io.IOException;

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

public class DevedorCadastroJanela extends Stage {

	AnchorPane root;

	public DevedorCadastroJanela(Pane pai) {

		setResizable(false);
		setTitle("Cadastro de Devedor");

		// Define a janela principal como dona desta
		initOwner(pai.getScene().getWindow());

		// Define o icone
		getIcons().add(new Image("icone/cedula.png"));

		// Define esta janela como modal, nao permitindo que se mexa na
		// principal enquanto esta nao for finalizada
		initModality(Modality.WINDOW_MODAL);
		
		try {
			root = FXMLLoader.load(getClass().getResource("/fxml/DevedorCadastro.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scene cena = new Scene(root);
		setScene(cena);

	}

}
