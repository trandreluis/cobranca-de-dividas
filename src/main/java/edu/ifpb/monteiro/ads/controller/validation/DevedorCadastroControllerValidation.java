package edu.ifpb.monteiro.ads.controller.validation;

import java.time.LocalDate;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DevedorCadastroControllerValidation {

	public boolean cadastrarDevedor(String nome, String cpf, LocalDate dataNascimento, String descricao, String valor,
			LocalDate dataDivida, String rua, String numero, String bairro, String cidade, String estado,
			String pontoReferencia) {

		return cadastrarDevedor(nome, cpf, dataNascimento) && cadastrarDivida(descricao, valor, dataDivida)
				&& cadastrarEndereco(rua, numero, bairro, cidade, estado, pontoReferencia);

	}

	private boolean cadastrarDevedor(String nome, String cpf, LocalDate dataNascimento) {

		if (nome.equals("") || nome == null || nome.length() > 70) {
			alertar("Nome");
			return false;
		} 
		
		else if(cpf.length() > 15) {
			alertar("CPF");
			return false;
		}
		
		try {
			Long.parseLong(cpf);
		} catch (Exception e) {
			alertar("CPF");
			return false;
		}
		
		if (dataNascimento == null) {
			alertar("Data de nascimento");
			return false;
		} 
			
		return true;
		
	}

	private boolean cadastrarDivida(String descricao, String valor, LocalDate dataDivida) {
		
		if (descricao.equals("") || descricao == null || descricao.length() > 60) {
			alertar("Descricao");
			return false;
		}
		
		try {
			if(Double.parseDouble(valor) <= 0) {
				alertar("Valor");
				return false;
			}
		} catch (Exception e) {
			alertar("Valor");
			return false;
		}
		
		if (dataDivida == null) {
			alertar("Data da divida");
			return false;
		}
		
		return true;
	}

	private boolean cadastrarEndereco(String rua, String numero, String bairro, String cidade, String estado,
			String pontoReferencia) {

		if (rua.equals("") || rua == null || rua.length() > 60) {
			alertar("Rua");
			return false;
		} else if (numero.equals("") || numero == null || numero.length() > 15) {
			alertar("Numero");
			return false;
		} else if (bairro.equals("") || bairro == null || bairro.length() > 60) {
			alertar("Bairro");
			return false;
		} else if (cidade.equals("") || cidade == null || cidade.length() > 60) {
			alertar("Cidade");
			return false;
		} else if (estado.equals("") || estado == null || estado.length() > 50) {
			alertar("Estado");
			return false;
		} else if (pontoReferencia.equals("") || pontoReferencia == null || pontoReferencia.length() > 50) {
			alertar("Ponto de referencia");
			return false;
		}
		return true;
	}

	private void alertar(String campoErrado) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erro!");
		alert.setHeaderText(null);
		alert.setContentText("Preencha o campo (" + campoErrado + ") corretamente!");
		alert.showAndWait();
	}

}
