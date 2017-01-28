package edu.ifpb.monteiro.ads.controller.validation;

import java.time.LocalDate;
import java.time.Period;

import edu.ifpb.monteiro.ads.util.ValidarCPF;
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
			alertar("Nome", false);
			return false;
		} 
		
		else if(cpf.length() != 14) {
			alertar("CPF inválido!", true);
			return false;
		} 
		
		cpf = ValidarCPF.pegarSomenteNumerosCPF(cpf);
		
		if(!ValidarCPF.isCPF(cpf)) {
			alertar("CPF Inválido!", true);
			return false;
		}
		
		if (dataNascimento == null) {
			alertar("Data de nascimento", false);
			return false;
		} 
			
		LocalDate dataAtual = LocalDate.now();
		Period periodo = Period.between(dataNascimento, dataAtual);

		int idade = periodo.getYears();
		
		if(idade < 18) {
			alertar("Muito novo!\nÉ preciso ter pelo menos 18 anos para ser um Devedor.", true);
			return false;
		}
		
		return true;
		
	}

	private boolean cadastrarDivida(String descricao, String valor, LocalDate dataDivida) {
		
		if (descricao.equals("") || descricao == null || descricao.length() > 60) {
			alertar("Descricao", false);
			return false;
		}
		
		try {
			if(Double.parseDouble(valor) <= 0) {
				alertar("Valor", false);
				return false;
			}
		} catch (Exception e) {
			alertar("Valor", false);
			return false;
		}
		
		if (dataDivida == null) {
			alertar("Data da divida", false);
			return false;
		}
		
		return true;
	}

	private boolean cadastrarEndereco(String rua, String numero, String bairro, String cidade, String estado,
			String pontoReferencia) {

		if (rua.equals("") || rua == null || rua.length() > 60) {
			alertar("Rua", false);
			return false;
		} else if (numero.equals("") || numero == null || numero.length() > 15) {
			alertar("Numero", false);
			return false;
		} 
		
		try {
			if(Double.parseDouble(numero) <= 0) {
				alertar("Numero", false);
				return false;
			}
		} catch (Exception e) {
			alertar("Valor", false);
			return false;
		}
		
		if (bairro.equals("") || bairro == null || bairro.length() > 60) {
			alertar("Bairro", false);
			return false;
		} else if (cidade.equals("") || cidade == null || cidade.length() > 60) {
			alertar("Cidade", false);
			return false;
		} else if (estado.equals("") || estado == null || estado.length() > 50) {
			alertar("Estado", false);
			return false;
		} else if (pontoReferencia.equals("") || pontoReferencia == null || pontoReferencia.length() > 50) {
			alertar("Ponto de referencia", false);
			return false;
		}
		return true;
	}
	
	private void alertar(String mensagemCampo, boolean mensagemCompleta) {
		
		if(mensagemCompleta) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro!");
			alert.setHeaderText(null);
			alert.setContentText(mensagemCampo);
			alert.showAndWait();
			
		}
		
		else {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro!");
			alert.setHeaderText(null);
			alert.setContentText("Preencha o campo (" + mensagemCampo + ") corretamente!");
			alert.showAndWait();			

		}
		
	}

}
