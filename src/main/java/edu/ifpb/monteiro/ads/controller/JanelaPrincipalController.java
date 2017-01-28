package edu.ifpb.monteiro.ads.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Optional;

import edu.ifpb.monteiro.ads.controller.validation.JanelaPrincipalControllerValidation;
import edu.ifpb.monteiro.ads.dao.DevedorDao;
import edu.ifpb.monteiro.ads.model.Devedor;
import edu.ifpb.monteiro.ads.view.DevedorCadastroJanela;
import edu.ifpb.monteiro.ads.view.JanelaNegociarCadastro;
import edu.ifpb.monteiro.ads.view.JanelaParcela;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
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
	private Button botaoAcompanharPagamento;

	@FXML
	private Button botaoNegociar;

	@FXML
	private Hyperlink linkGithub;

	private DevedorDao daoDevedor = new DevedorDao();

	private JanelaPrincipalControllerValidation validador = new JanelaPrincipalControllerValidation();

	@FXML
	public void initialize() {

		linkGithub.setFocusTraversable(false);

		ObservableList<Devedor> devedoresObservableList = FXCollections.observableArrayList(daoDevedor.buscarTodos());

		colunaNomeDevedor.setCellValueFactory(valorDaCelula -> valorDaCelula.getValue().nome());
		colunaCpfDevedor.setCellValueFactory(valorDaCelula -> valorDaCelula.getValue().cpf());
		colunaIdadeDevedor.setCellValueFactory(valorDaCelula -> valorDaCelula.getValue().idade().asObject());
		colunaDividaDevedor.setCellValueFactory(valorDaCelula -> valorDaCelula.getValue().divida().asObject());
		colunaDataDivida.setCellValueFactory(valorDaCelula -> valorDaCelula.getValue().getDivida().dataDivida());

		tabelaDevedores.setItems(devedoresObservableList);

	}

	public void cliqueLinkGithub() {

		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.browse(new URI("https://www.github.com/trandreluis"));
		} catch (IOException | URISyntaxException e) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro!");
			alert.setHeaderText(null);
			alert.setContentText("Não foi possível abrir o site!");
			alert.showAndWait();

		}

	}

	@FXML
	public void buscarPeloNome() {
		if (fieldBuscaNome.getText().equals("")) {
			atualizarTabelaDevedorCompleta();
		} else {
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

		int linhaSelecionada = tabelaDevedores.getSelectionModel().getSelectedIndex();

		if (validador.editarDevedor(linhaSelecionada)) {

			Integer idDevedor = tabelaDevedores.getSelectionModel().getSelectedItem().getId();

			DevedorDao daoDevedor = new DevedorDao();

			Devedor devedorVerificado = daoDevedor.buscarPorID(idDevedor);

			if (!daoDevedor.negociouDivida(devedorVerificado.getDivida().getId())) {

				DevedorCadastroJanela cadastroDevedor = new DevedorCadastroJanela(root);
				cadastroDevedor.show();
				Devedor devedor = tabelaDevedores.getSelectionModel().getSelectedItem();
				DevedorCadastroJanela.getController().preencherCampos(devedor, true);

			} else {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro!");
				alert.setHeaderText(null);
				alert.setContentText("Este Devedor já negociou sua dívida, é impossível editá-lo!");
				alert.showAndWait();

			}
		}
	}

	@FXML
	public void excluirDevedor() {

		int linhaSelecionada = tabelaDevedores.getSelectionModel().getSelectedIndex();

		if (validador.excluirDevedor(linhaSelecionada)) {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmação");
			alert.setHeaderText("Por favor, preste atenção, esta decisão é irreversível!");
			alert.setContentText("Tem certeza que deseja excluir o Devedor?");

			Optional<ButtonType> resultado = alert.showAndWait();
			if (resultado.get() == ButtonType.OK) {
				Integer idDevedor = tabelaDevedores.getSelectionModel().getSelectedItem().getId();

				DevedorDao daoDevedor = new DevedorDao();
				daoDevedor.excluir(idDevedor);

				atualizarTabelaDevedorCompleta();
			}

		}

	}

	@FXML
	public void expandirDevedor() {

		int linhaSelecionada = tabelaDevedores.getSelectionModel().getSelectedIndex();

		if (validador.expandirDevedor(linhaSelecionada)) {

			DevedorCadastroJanela cadastroDevedor = new DevedorCadastroJanela(root);
			cadastroDevedor.show();

			Devedor devedor = tabelaDevedores.getSelectionModel().getSelectedItem();

			DevedorCadastroJanela.getController().preencherCampos(devedor, false);
		}

	}

	@FXML
	public void acompanharPagamento() {

		int linhaSelecionada = tabelaDevedores.getSelectionModel().getSelectedIndex();

		if (validador.acompanharPagamento(linhaSelecionada)) {

			Integer idDevedor = tabelaDevedores.getSelectionModel().getSelectedItem().getId();

			DevedorDao daoDevedor = new DevedorDao();

			Devedor devedor = daoDevedor.buscarPorID(idDevedor);

			if (daoDevedor.negociouDivida(devedor.getDivida().getId())) {

				JanelaParcela janelaParcela = new JanelaParcela(root);
				JanelaParcela.getController().preecherTabelaParcelas(devedor);
				janelaParcela.show();

			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro!");
				alert.setHeaderText(null);
				alert.setContentText(
						"Este Devedor ainda não negociou sua dívida!\nÉ possível negociar acessando a opção (Negociar!).");
				alert.showAndWait();
			}

		}

	}

	@FXML
	public void negociarComDevedor() {

		int linhaSelecionada = tabelaDevedores.getSelectionModel().getSelectedIndex();

		if (validador.negociarComDevedor(linhaSelecionada)) {

			Devedor devedor = tabelaDevedores.getSelectionModel().getSelectedItem();

			if (!daoDevedor.negociouDivida(devedor.getDivida().getId())) {
				JanelaNegociarCadastro negociarCadastro = new JanelaNegociarCadastro(root);
				JanelaNegociarCadastro.getController().preencherCampos(devedor);

				negociarCadastro.show();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro!");
				alert.setHeaderText(null);
				alert.setContentText(
						"Este Devedor já negociou sua dívida!\nÉ possível acompanhar o negócio acessando a opção (Acompanhar pagamento).");
				alert.showAndWait();
			}
		}
	}

	public void atualizarTabelaDevedorCompleta() {
		tabelaDevedores.getItems().setAll(buscarDevedoresCompleto());
	}

	private ObservableList<Devedor> buscarDevedoresCompleto() {
		ObservableList<Devedor> listDevedores = FXCollections.observableArrayList();
		listDevedores.setAll(daoDevedor.buscarTodos());
		return listDevedores;
	}

	public void atualizarTabelaDevedorPeloNome() {
		tabelaDevedores.getItems().setAll(buscarDevedoresPeloNome());
	}

	private ObservableList<Devedor> buscarDevedoresPeloNome() {
		ObservableList<Devedor> listDevedores = FXCollections.observableArrayList();
		listDevedores.setAll(daoDevedor.buscarPorNome(fieldBuscaNome.getText()));
		return listDevedores;
	}

}
