package edu.ifpb.monteiro.ads.controller;

import edu.ifpb.monteiro.ads.dao.DevedorDao;
import edu.ifpb.monteiro.ads.dao.DividaDao;
import edu.ifpb.monteiro.ads.dao.EnderecoDao;
import edu.ifpb.monteiro.ads.model.Devedor;
import edu.ifpb.monteiro.ads.model.Divida;
import edu.ifpb.monteiro.ads.model.Endereco;
import edu.ifpb.monteiro.ads.view.MainApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 
 * @author Andre Luis
 * 
 *         e-mail: tr.andreluis@gmail.com
 *
 */

public class DevedorCadastroController {

	@FXML
	private AnchorPane root;

	@FXML
	private TextField fieldNomeDevedor;

	@FXML
	private TextField fieldCpfDevedor;

	@FXML
	private TextField fieldRuaEndereco;

	@FXML
	private TextField fieldNumeroEndereco;

	@FXML
	private TextField fieldBairroEndereco;

	@FXML
	private TextField fieldEstadoEndereco;

	@FXML
	private TextField fieldCidadeEndereco;

	@FXML
	private TextField fieldPontoReferenciaEndereco;

	@FXML
	private TextField fieldDescricaoDivida;

	@FXML
	private TextField fieldValorDivida;

	@FXML
	private DatePicker dateDivida;

	@FXML
	private DatePicker dateNascimentoDevedor;
	
	@FXML
	private Label labelNaoEditavel;

	@FXML
	private Button botaoCadastrar;

	@FXML
	private Button botaoCancelar;

	@FXML
	public void cadastrarDevedor() {

		DividaDao daoDivida = new DividaDao();

		Divida divida = new Divida(Double.parseDouble(fieldValorDivida.getText()), dateDivida.getValue(),
				fieldDescricaoDivida.getText());

		Integer idDivida = daoDivida.salvar(divida);

		divida.setId(idDivida);

		EnderecoDao daoEndereco = new EnderecoDao();

		Endereco endereco = new Endereco(fieldRuaEndereco.getText(), fieldNumeroEndereco.getText(),
				fieldBairroEndereco.getText(), fieldCidadeEndereco.getText(), fieldEstadoEndereco.getText(),
				fieldPontoReferenciaEndereco.getText());

		Integer idEndereco = daoEndereco.salvar(endereco);

		endereco.setId(idEndereco);

		DevedorDao daoDevedor = new DevedorDao();

		Devedor devedor = new Devedor(divida, fieldNomeDevedor.getText(), fieldCpfDevedor.getText(),
				dateNascimentoDevedor.getValue(), endereco);

		daoDevedor.salvar(devedor);

		fecharStage();

		atualizar();

	}

	public void atualizar() {
		MainApp.getController().atualizarTabelaDevedorCompleta();
	}

	@FXML
	public void cancelarCadastro() {
		fecharStage();
	}

	public void preencherCampos(Devedor devedor, boolean edicao) {

		//Campo Devedor
		fieldNomeDevedor.setText(devedor.getNome());
		fieldCpfDevedor.setText(devedor.getCpf());
		dateNascimentoDevedor.setValue(devedor.getDataNascimento());

		//Campo Divida
		fieldDescricaoDivida.setText(devedor.getDivida().getDescricao());
		fieldValorDivida.setText(devedor.getDivida().getValor().toString());
		dateDivida.setValue(devedor.getDivida().getDataDivida());
		
		//Campo Endereco
		fieldRuaEndereco.setText(devedor.getEndereco().getRua());
		fieldNumeroEndereco.setText(devedor.getEndereco().getNumero());
		fieldBairroEndereco.setText(devedor.getEndereco().getBairro());
		fieldCidadeEndereco.setText(devedor.getEndereco().getCidade());
		fieldEstadoEndereco.setText(devedor.getEndereco().getEstado());
		fieldPontoReferenciaEndereco.setText(devedor.getEndereco().getPontoReferencia());
		
		//Botao Editar
		botaoCadastrar.setText("Editar");
		botaoCadastrar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				salvarEdicao(devedor);
			}
		});
		
		if(!edicao) {
			
			//Alterando botoes
			botaoCadastrar.setVisible(false);;
			botaoCancelar.setText("Voltar");
			
			//Habilitando label com mensagem
			labelNaoEditavel.setVisible(true);
			
			fieldNomeDevedor.setEditable(false);
			fieldCpfDevedor.setEditable(false);
			dateNascimentoDevedor.setEditable(false);
			dateNascimentoDevedor.setDisable(true);
			
			fieldDescricaoDivida.setEditable(false);
			fieldValorDivida.setEditable(false);
			dateDivida.setEditable(false);
			dateDivida.setDisable(true);
			
			fieldRuaEndereco.setEditable(false);
			fieldNumeroEndereco.setEditable(false);
			fieldBairroEndereco.setEditable(false);
			fieldCidadeEndereco.setEditable(false);
			fieldEstadoEndereco.setEditable(false);
			fieldPontoReferenciaEndereco.setEditable(false);

		} 
		
	}
	
	private void salvarEdicao(Devedor devedor) {
		
		DividaDao daoDivida = new DividaDao();
		
		Divida dividaAlterada = new Divida(Double.parseDouble(fieldValorDivida.getText()), dateDivida.getValue(),
				fieldDescricaoDivida.getText());
		
		dividaAlterada.setId(devedor.getDivida().getId());
		
		daoDivida.atualizar(dividaAlterada);
		
		EnderecoDao daoEndereco = new EnderecoDao();
		
		Endereco enderecoAlterado = new Endereco(fieldRuaEndereco.getText(), fieldNumeroEndereco.getText(),
				fieldBairroEndereco.getText(), fieldCidadeEndereco.getText(), fieldEstadoEndereco.getText(),
				fieldPontoReferenciaEndereco.getText());
		
		enderecoAlterado.setId(devedor.getEndereco().getId());
		
		daoEndereco.atualizar(enderecoAlterado);
		
		DevedorDao daoDevedor = new DevedorDao();
		
		Devedor devedorAlterado = new Devedor(dividaAlterada, fieldNomeDevedor.getText(), fieldCpfDevedor.getText(),
				dateNascimentoDevedor.getValue(), enderecoAlterado);
		devedorAlterado.setId(devedor.getId());
		
		daoDevedor.atualizar(devedorAlterado);
		
		fecharStage();
		
		atualizar();
		
	}
	
	@FXML
	public void fecharStage() {
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}

}
