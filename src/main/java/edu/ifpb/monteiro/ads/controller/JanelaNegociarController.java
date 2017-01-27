package edu.ifpb.monteiro.ads.controller;

import java.text.DecimalFormat;
import java.text.ParseException;

import edu.ifpb.monteiro.ads.model.Devedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
