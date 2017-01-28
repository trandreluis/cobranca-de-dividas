package edu.ifpb.monteiro.ads.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;

import edu.ifpb.monteiro.ads.dao.ParcelaDao;
import edu.ifpb.monteiro.ads.model.Devedor;
import edu.ifpb.monteiro.ads.model.Parcela;
import edu.ifpb.monteiro.ads.view.JanelaNegociarCadastro;
import edu.ifpb.monteiro.ads.view.JanelaParcela;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @author Andre Luis
 * 
 *         e-mail: tr.andreluis@gmail.com
 *
 */

public class JanelaNegociarController {

	@FXML
	private AnchorPane root;

	@FXML
	private Button botaoNegociar;

	@FXML
	private Button botaoCancelar;

	@FXML
	private Text textNomeDevedor;

	@FXML
	private Text textValorDivida;

	@FXML
	private Text textParcelaMensal;

	@FXML
	private ComboBox<String> comboOpcoesParcelamento;

	private DecimalFormat formato = new DecimalFormat("#0.00");
	
	private Devedor devedor;

	@FXML
	public void initialize() {

		ObservableList<String> opcoes = FXCollections.observableArrayList();
		opcoes.add("1x sem juros");
		opcoes.add("2x sem juros");
		opcoes.add("3x sem juros");
		opcoes.add("4x sem juros");
		opcoes.add("5x sem juros");
		opcoes.add("6x sem juros");
		opcoes.add("7x sem juros");
		opcoes.add("8x sem juros");
		opcoes.add("9x sem juros");
		opcoes.add("10x sem juros");
		opcoes.add("11x sem juros");
		opcoes.add("12x sem juros");

		comboOpcoesParcelamento.getItems().setAll(opcoes);

	}

	@FXML
	public void fazerNegocio() {

		ParcelaDao daoParcela = new ParcelaDao();
		
		int quantidadeParcelas = comboOpcoesParcelamento.getSelectionModel().getSelectedIndex() + 1;
		
		Double valorDivida = null;
		try {
			valorDivida = formato.parse(textValorDivida.getText()).doubleValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Double valorParcela = (valorDivida / quantidadeParcelas);
		
		try {
			valorParcela = formato.parse(valorParcela.toString()).doubleValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		LocalDate dataAtual = LocalDate.now();
		
		for(int i=1; i <= quantidadeParcelas; i++) {
			
			Parcela parcela = new Parcela(devedor.getDivida(), valorParcela, dataAtual.withMonth(i+1));
			daoParcela.salvar(parcela);
			
		}
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Sucesso!");
		alert.setHeaderText(null);
		alert.setContentText("O negócio foi fechado!");

		alert.showAndWait();
		
		fecharStage();
		
		JanelaParcela janelaParcela = new JanelaParcela(JanelaNegociarCadastro.getPai());
		JanelaParcela.getController().preecherTabelaParcelas(devedor);
		janelaParcela.show();
		
	}

	@FXML
	public void calcularParcela() {

		if (comboOpcoesParcelamento.getSelectionModel().getSelectedIndex() != -1) {
			int quantidadeParcelas = comboOpcoesParcelamento.getSelectionModel().getSelectedIndex() + 1;

			Double valorDivida = null;
			try {
				valorDivida = formato.parse(textValorDivida.getText()).doubleValue();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Double valorParcela = (valorDivida / quantidadeParcelas);

			String parcelaString = formato.format(valorParcela);

			textParcelaMensal.setText(parcelaString);
		}

	}

	@FXML
	public void cancelarNegocio() {
		fecharStage();
	}

	public void preencherCampos(Devedor devedor) {
		
		this.devedor = devedor;

		textNomeDevedor.setText(devedor.getNome());

		Double valorParcela = devedor.getDivida().getValor();

		String parcelaString = formato.format(valorParcela);

		textValorDivida.setText(parcelaString);

	}

	@FXML
	public void fecharStage() {
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}

}
