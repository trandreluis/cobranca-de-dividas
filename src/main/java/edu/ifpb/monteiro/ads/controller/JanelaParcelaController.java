package edu.ifpb.monteiro.ads.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import edu.ifpb.monteiro.ads.controller.validation.JanelaParcelaControllerValidation;
import edu.ifpb.monteiro.ads.dao.DividaDao;
import edu.ifpb.monteiro.ads.dao.ParcelaDao;
import edu.ifpb.monteiro.ads.model.Devedor;
import edu.ifpb.monteiro.ads.model.Divida;
import edu.ifpb.monteiro.ads.model.Parcela;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
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
	
	@FXML
	private Text textDescricaoDivida;
	
	@FXML
	private Text textValorTotal;
	
	@FXML
	private Text textParcelasPagas;
	
	@FXML
	private Text textStatusDivida;
	
	@FXML
	private Text textMensagemPagamento;

	private Devedor devedor;

	private ParcelaDao daoParcela = new ParcelaDao();

	private static boolean primeiraVez = true;
	
	private JanelaParcelaControllerValidation validador = new JanelaParcelaControllerValidation();

	@FXML
	public void initialize() {

	}

	public void preecherTabelaParcelas(Devedor devedor) {

		this.devedor = devedor;

		DividaDao daoDivida = new DividaDao();
		
		Divida divida = devedor.getDivida();
		
		textDescricaoDivida.setText(daoDivida.buscarPorID(devedor.getDivida().getId()).getDescricao());
		textValorTotal.setText(divida.getValor().toString());
		
		
		ObservableList<Parcela> parcelasObservableList = FXCollections
				.observableArrayList(daoParcela.buscarPorDivida(devedor.getDivida().getId()));

		colunaValorParcela.setCellValueFactory(valorDaCelula -> valorDaCelula.getValue().valorParcela().asObject());
		colunaDataParcela.setCellValueFactory(valorDaCelula -> valorDaCelula.getValue().dataParcela());
		colunaParcelaAtrasada.setCellValueFactory(valorDaCelula -> valorDaCelula.getValue().atrasada());
		colunaParcelaPaga.setCellValueFactory(valorDaCelula -> valorDaCelula.getValue().paga());

		tabelaParcelas.setItems(parcelasObservableList);
		
		Integer quantidadeParcelasPagas = new Integer(0);
		
		for(Parcela p : parcelasObservableList) {
			if(p.getPaga()) {
				quantidadeParcelasPagas++;
			}
		}
		
		textParcelasPagas.setText(quantidadeParcelasPagas.toString());
		
		if(quantidadeParcelasPagas == parcelasObservableList.size()) {
			textStatusDivida.setText("PAGA");
			textMensagemPagamento.setVisible(true);
			botaoCancelarPagamento.setDisable(true);
			botaoPagarParcela.setDisable(true);
		} else {
			textStatusDivida.setText("EM PAGAMENTO");
			textMensagemPagamento.setVisible(false);
		}

		preencherAtualizarGraficos();

	}

	@FXML
	public void pagarParcela() {

		int linhaSelecionada = tabelaParcelas.getSelectionModel().getSelectedIndex();

		if (validador.pagarParcela(linhaSelecionada)) {
			
			ParcelaDao daoParcela = new ParcelaDao();
			
			ArrayList<Parcela> parcelas = daoParcela.buscarPorDivida(devedor.getDivida().getId());
			
			int quantidadeQueFaltam = 0;
			
			for(Parcela p : parcelas) {
				if(!p.getPaga()) {
					quantidadeQueFaltam++;
				}
			}
			
			Parcela parcela = tabelaParcelas.getSelectionModel().getSelectedItem();
			
			if(!parcela.getPaga()) {
				
				if(quantidadeQueFaltam == 1) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Confirmação");
					alert.setHeaderText("Preste atenção, esta decisão não é reversível!");
					alert.setContentText("Tem certeza que deseja quitar a Divida?");

					Optional<ButtonType> resultado = alert.showAndWait();
					if (resultado.get() == ButtonType.OK) {
						parcela.setPaga(true);
						daoParcela.atualizar(parcela);
						preecherTabelaParcelas(devedor);
					}
				} else {					
					parcela.setPaga(true);
					daoParcela.atualizar(parcela);
					preecherTabelaParcelas(devedor);
				}
				
				
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro!");
				alert.setHeaderText(null);
				alert.setContentText(
						"Esta parcela já foi paga, por isso não pode ser paga novamente!");
				alert.showAndWait();
			}
		}
	}

	@FXML
	public void cancelarPagamento() {

		int linhaSelecionada = tabelaParcelas.getSelectionModel().getSelectedIndex();

		if (validador.cancelarPagamento(linhaSelecionada)) {
			
			ParcelaDao daoParcela = new ParcelaDao();
			
			Parcela parcela = tabelaParcelas.getSelectionModel().getSelectedItem();
			
			if(parcela.getPaga()) {
				
				parcela.setPaga(false);				
				daoParcela.atualizar(parcela);
				preecherTabelaParcelas(devedor);
				
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro!");
				alert.setHeaderText(null);
				alert.setContentText(
						"Esta parcela não foi paga, por isso não pode ter o pagamento cancelado!");
				alert.showAndWait();
			}
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
