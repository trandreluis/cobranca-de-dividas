package edu.ifpb.monteiro.ads.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import edu.ifpb.monteiro.ads.controller.validation.JanelaParcelaControllerValidation;
import edu.ifpb.monteiro.ads.dao.ParcelaDao;
import edu.ifpb.monteiro.ads.model.Devedor;
import edu.ifpb.monteiro.ads.model.Parcela;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 
 * @author Andre Luis
 * 
 *         e-mail: tr.andreluis@gmail.com
 *
 */

public class JanelaParcelaController {

	@FXML
	private AnchorPane root;

	@FXML
	private TableView<Parcela> tabelaParcelas;

	@FXML
	private TableColumn<Parcela, Double> colunaValorParcela;

	@FXML
	private TableColumn<Parcela, LocalDate> colunaDataParcela;

	@FXML
	private TableColumn<Parcela, String> colunaParcelaAtrasada;

	@FXML
	private TableColumn<Parcela, String> colunaParcelaPaga;

	@FXML
	private Button botaoPagarParcela;

	@FXML
	private Button botaoCancelarPagamento;

	@FXML
	private Button botaoVoltar;

	@FXML
	private PieChart graficoPizza;

	private Devedor devedor;

	private ParcelaDao daoParcela = new ParcelaDao();

	private static boolean primeiraVez = true;
	
	private JanelaParcelaControllerValidation validador = new JanelaParcelaControllerValidation();

	@FXML
	public void initialize() {

	}

	public void preecherTabelaParcelas(Devedor devedor) {

		this.devedor = devedor;

		ObservableList<Parcela> devedoresObservableList = FXCollections
				.observableArrayList(daoParcela.buscarPorDivida(devedor.getDivida().getId()));

		colunaValorParcela.setCellValueFactory(valorDaCelula -> valorDaCelula.getValue().valorParcela().asObject());
		colunaDataParcela.setCellValueFactory(valorDaCelula -> valorDaCelula.getValue().dataParcela());
		colunaParcelaAtrasada.setCellValueFactory(valorDaCelula -> valorDaCelula.getValue().atrasada());
		colunaParcelaPaga.setCellValueFactory(valorDaCelula -> valorDaCelula.getValue().paga());

		tabelaParcelas.setItems(devedoresObservableList);

		preencherAtualizarGraficos();

	}

	@FXML
	public void pagarParcela() {

		int linhaSelecionada = tabelaParcelas.getSelectionModel().getSelectedIndex();

		if (validador.pagarParcela(linhaSelecionada)) {
			
			ParcelaDao daoParcela = new ParcelaDao();
			
			Parcela parcela = tabelaParcelas.getSelectionModel().getSelectedItem();
			parcela.setPaga(true);
			
			daoParcela.atualizar(parcela);
			
			preecherTabelaParcelas(devedor);
		
		}

	}

	@FXML
	public void cancelarPagamento() {

		int linhaSelecionada = tabelaParcelas.getSelectionModel().getSelectedIndex();

		if (validador.cancelarPagamento(linhaSelecionada)) {
			
			ParcelaDao daoParcela = new ParcelaDao();
			
			Parcela parcela = tabelaParcelas.getSelectionModel().getSelectedItem();
			parcela.setPaga(false);
			
			daoParcela.atualizar(parcela);
			
			preecherTabelaParcelas(devedor);

		}
	}

	public void preencherAtualizarGraficos() {

		ArrayList<Parcela> parcelas = daoParcela.buscarPorDivida(devedor.getDivida().getId());

		int quantidadeParcelasPagas = 0;

		for (Parcela p : parcelas) {
			if (p.getPaga()) {
				quantidadeParcelasPagas++;
			}

		}

		if (!primeiraVez) {
			graficoPizza.getData().clear();
		}

		int quantidadeParcelasNaoPagas = parcelas.size() - quantidadeParcelasPagas;
		graficoPizza.getData().addAll(new PieChart.Data("Parcelas pagas", quantidadeParcelasPagas),
				new PieChart.Data("Parcelas não pagas", quantidadeParcelasNaoPagas));

		primeiraVez = false;

	}

	public void voltar() {
		fecharStage();
	}

	@FXML
	public void fecharStage() {
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}

}
