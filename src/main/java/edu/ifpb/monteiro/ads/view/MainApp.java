package edu.ifpb.monteiro.ads.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 
 * @author Andre Luis
 * 
 *         e-mail: tr.andreluis@gmail.com
 *
 */

public class MainApp extends Application {

	private AnchorPane root;
	private static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		MainApp.primaryStage = primaryStage;
		
		//Definindo icone
		MainApp.primaryStage.getIcons().add(new Image("/icone/cedula.png"));
		
		//Definindo e carregando o FXML pra a janela principal
		root = FXMLLoader.load(getClass().getResource("/fxml/JanelaPrincipal.fxml"));
		
		//Definicao da Scena a ser exibida
		Scene cena = new Scene(root);
		MainApp.primaryStage.setScene(cena);
		MainApp.primaryStage.show();
		
		//Encerrar a aplicacao quando a janela princiapl for fechada
		MainApp.primaryStage.setOnCloseRequest(e -> System.exit(0));
		
	}

	public static Stage getPrimaryStage() {
        return primaryStage;
    }
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
