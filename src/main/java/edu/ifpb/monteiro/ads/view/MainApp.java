package edu.ifpb.monteiro.ads.view;

import java.io.File;

import javax.swing.JOptionPane;

import edu.ifpb.monteiro.ads.banco.Tabelas;
import edu.ifpb.monteiro.ads.controller.JanelaPrincipalController;
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
	private static FXMLLoader fxml;

	@Override
	public void start(Stage primaryStage) throws Exception {

		MainApp.primaryStage = primaryStage;
		
		primaryStage.setTitle("SACD - Sistema de Apoio a Cobrança de Dividas");

		// Definindo icone
		MainApp.primaryStage.getIcons().add(new Image("/icone/cedula.png"));

		fxml = new FXMLLoader(getClass().getResource("/fxml/JanelaPrincipal.fxml"));

		// Definindo e carregando o FXML pra a janela principal
		root = (AnchorPane) fxml.load();

		// Definicao da Scena a ser exibida
		Scene cena = new Scene(root);
		MainApp.primaryStage.setScene(cena);
		MainApp.primaryStage.show();

		// Encerrar a aplicacao quando a janela principal for fechada
		MainApp.primaryStage.setOnCloseRequest(e -> System.exit(0));

	}

	public static JanelaPrincipalController getController() {

		JanelaPrincipalController controller = (JanelaPrincipalController) fxml.getController();

		return controller;
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {

		File file = new File("banco-dividas/");

		if (file.exists()) {
			JOptionPane.showMessageDialog(null, "Banco de dados local encontrado!");
		}

		else {
			JOptionPane.showMessageDialog(null, "Criando banco de dados local!");
			Tabelas tabelas = new Tabelas();
			tabelas.criarBanco();
		}

		launch(args);
	}

}
