package edu.ifpb.monteiro.ads.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private AnchorPane root;
	private static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		MainApp.primaryStage = primaryStage;
		
		root = FXMLLoader.load(getClass().getResource("/fxml/JanelaPrincipal.fxml"));
		
		//Definicao da Scena a ser exibida
		Scene cena = new Scene(root);
		MainApp.primaryStage.setScene(cena);
		MainApp.primaryStage.show();
		
		MainApp.primaryStage.setOnCloseRequest(e -> System.exit(0));
		
	}

	public static Stage getPrimaryStage() {
        return primaryStage;
    }
	
	public static void main(String[] args) {
		
		launch(args);
		
	}
	
}
