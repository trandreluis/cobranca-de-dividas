package edu.ifpb.monteiro.ads.controller;

import java.time.LocalDate;
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
import javafx.scene.control.TextField;
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
	private TextField fieldBuscaNome;
	
	@FXML
	private TableView<Devedor> tabelaDevedores;

	@FXML
	private TableColumn<Devedor, String> colunaNomeDevedor;

	@FXML
	private TableColumn<Devedor, String> colunaCpfDevedor;

	@FXML
	private TableColumn<Devedor, Integer> colunaIdadeDevedor;

	@FXML
	private TableColumn<Devedor, Double> colunaDividaDevedor;
	
	@FXML
	private TableColumn<Devedor, LocalDate> colunaDataDivida;

	@FXML
	private Button botaoBuscar;

	@FXML
	private Button botaoNovoDevedor;

	@FXML
	private Button botaoEditarDevedor;

	@FXML
	private Button botaoExcluirDevedor;
	
	@FXML
	private Button botaoExpandirDevedor;

	@FXML
	private Button botaoNegociar;
	
	private DevedorDao daoDevedor  = new DevedorDao();
	
	@FXML
	public void initialize() {

		ObservableList<Devedor> devedoresObservableList = FXCollections.observableArrayList();

		devedoresObservableList = FXCollections.observableArrayList();
		
		ArrayList<Devedor> devedoresList = daoDevedor.buscarTodos();

		for (Devedor d : devedoresList) {
			devedoresObservableList.add(d);
		}

		colunaNomeDevedor.setCellValueFactory(valorDaCelula -> valorDaCelula.getValue().nome());
		colunaCpfDevedor.setCellValueFactory(valorDaCelula -> valorDaCelula.getValue().cpf());
		colunaIdadeDevedor.setCellValueFactory(valorDaCelula -> valorDaCelula.getValue().idade().asObject());
		colunaDividaDevedor.setCellValueFactory(valorDaCelula -> valorDaCelula.getValue().divida().asObject());
		colunaDataDivida.setCellValueFactory(valorDaCelula -> valorDaCelula.getValue().getDivida().dataDivida());

		tabelaDevedores.setItems(devedoresObservableList);
		
	}

	@FXML
	public void buscarPeloNome() {
		if(fieldBuscaNome.getText().equals("")) {
			atualizarTabelaDevedorCompleta();
		}else {
			atualizarTabelaDevedorPeloNome();			
		}
	}
	
	@FXML
	public void novoDevedor() {
		DevedorCadastroJanela devedorCadastro = new DevedorCadastroJanela(root);
		devedorCadastro.show();
	}

	@FXML
	public void editarDevedor() {

		Alert alerta = new Alert(AlertType.INFORMATION);

		alerta.setTitle("Informacao");
		alerta.setHeaderText("Sucesso!");
		alerta.setContentText("Editar devedor!");

		alerta.showAndWait();

	}

	@FXML
	public void excluirDevedor() {

		Integer idDevedor = tabelaDevedores.getSelectionModel().getSelectedItem().getId();

		DevedorDao daoDevedor = new DevedorDao();
		daoDevedor.excluir(idDevedor);
		
		atualizarTabelaDevedorCompleta();
		
	}
	
	@FXML
	public void expandirDevedor() {
		
		Alert alerta = new Alert(AlertType.INFORMATION);

		alerta.setTitle("Informacao");
		alerta.setHeaderText("Sucesso!");
		alerta.setContentText("Expandir devedor!");

		alerta.showAndWait();
		
		DevedorCadastroJanela cadastroDevedor = new DevedorCadastroJanela(root);
		cadastroDevedor.show();
		
		Devedor devedor = tabelaDevedores.getSelectionModel().getSelectedItem();

		DevedorCadastroJanela.getController().preencherDesabilitarCampos(devedor);
		
	}

	@FXML
	public void negociarComDevedor() {

		Alert alerta = new Alert(AlertType.INFORMATION);

		alerta.setTitle("Informacao");
		alerta.setHeaderText("Sucesso!");
		alerta.setContentText("Negociar com devedor!");

		alerta.showAndWait();

	}
	
	public void atualizarTabelaDevedorCompleta() {
		tabelaDevedores.getItems().setAll(buscarDevedoresCompleto());
	}
	
	private ObservableList<Devedor> buscarDevedoresCompleto() {
        ObservableList<Devedor> listDevedores = FXCollections
                .observableArrayList();
        listDevedores.setAll(daoDevedor.buscarTodos());
        return listDevedores;
    }
	
	public void atualizarTabelaDevedorPeloNome() {
		tabelaDevedores.getItems().setAll(buscarDevedoresPeloNome());
	}
	
	private ObservableList<Devedor> buscarDevedoresPeloNome() {
        ObservableList<Devedor> listDevedores = FXCollections
                .observableArrayList();
        listDevedores.setAll(daoDevedor.buscarPorNome(fieldBuscaNome.getText()));
        return listDevedores;
    }

}
