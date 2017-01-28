package edu.ifpb.monteiro.ads.controller.validation;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class JanelaPrincipalControllerValidation {

	public boolean editarDevedor(int linhaSelecionada) {
		if(!analisarLinha(linhaSelecionada)) {
			alertar("editar");
			return false;
		}
		return true;
	}
	
	public boolean excluirDevedor(int linhaSelecionada) {
		if(!analisarLinha(linhaSelecionada)) {
			alertar("excluir");
			return false;
		}
		return true;
	}
	
	public boolean expandirDevedor(int linhaSelecionada) {
		if(!analisarLinha(linhaSelecionada)) {
			alertar("expandir");
			return false;
		}
		return true;
	}
	
	public boolean acompanharPagamento(int linhaSelecionada) {
		if(!analisarLinha(linhaSelecionada)) {
			alertar("acompanhar");
			return false;
		}
		return true;
	}
	
	public boolean negociarComDevedor(int linhaSelecionada) {
		if(!analisarLinha(linhaSelecionada)) {
			alertar("negociar");
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
		alert.setContentText("Selecione um Devedor para poder " + funcao + "!");
		alert.showAndWait();
	}
	
}
