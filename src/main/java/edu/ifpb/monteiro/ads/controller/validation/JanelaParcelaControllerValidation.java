package edu.ifpb.monteiro.ads.controller.validation;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class JanelaParcelaControllerValidation {

	public boolean pagarParcela(int linhaSelecionada) {
		if(!analisarLinha(linhaSelecionada)) {
			alertar("pagá-la");
			return false;
		}
		return true;
	}
	
	public boolean cancelarPagamento(int linhaSelecionada) {
		if(!analisarLinha(linhaSelecionada)) {
			alertar("cancelar o pagamento");
			return false;
		}
		return true;
	}
	
	private boolean analisarLinha(int linha) {
		if(linha >= 0) {
			return true;
		}
		return false;
	}
	
	private void alertar(String funcao) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erro!");
		alert.setHeaderText(null);
		alert.setContentText("Selecione uma Parcela para poder " + funcao + "!");
		alert.showAndWait();
	}
	
}
