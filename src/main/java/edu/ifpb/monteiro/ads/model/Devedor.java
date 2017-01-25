package edu.ifpb.monteiro.ads.model;

import java.time.LocalDate;
import java.time.Period;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author Andre Luis
 * 
 *         e-mail: tr.andreluis@gmail.com
 *
 */

public class Devedor {

	private IntegerProperty id;
	private Divida divida;
	private StringProperty nome;
	private StringProperty cpf;
	private ObjectProperty<LocalDate> dataNascimento;
	private Endereco endereco;

	public Devedor() {

	}

	public Devedor(Divida divida, String nome, String cpf, LocalDate dataNascimento, Endereco endereco) {

		this.divida = divida;
		this.nome = new SimpleStringProperty(nome);
		this.cpf = new SimpleStringProperty(cpf);
		this.dataNascimento = new SimpleObjectProperty<LocalDate>(dataNascimento);
		this.endereco = endereco;

	}

	public Integer getId() {
		return this.id.get();
	}

	public void setId(Integer id) {
		this.id = new SimpleIntegerProperty(id);
	}

	public Divida getDivida() {
		return this.divida;
	}

	public void setDivida(Divida divida) {
		this.divida = divida;
	}

	public String getNome() {
		return nome.get();
	}

	public void setNome(String nome) {
		this.nome = new SimpleStringProperty(nome);
	}

	public String getCpf() {
		return this.cpf.get();
	}

	public void setCpf(String cpf) {
		this.cpf = new SimpleStringProperty(cpf);
	}

	public LocalDate getDataNascimento() {
		return this.dataNascimento.get();
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = new SimpleObjectProperty<LocalDate>(dataNascimento);
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public StringProperty nome() {
		return this.nome;
	}
	
	public StringProperty cpf() {
		return this.cpf;
	}

	// Calcula a idade do devedor de acordo com sua data de nascimento
	public IntegerProperty idade() {

		LocalDate dataAtual = LocalDate.now();
		Period periodo = Period.between(this.dataNascimento.get(), dataAtual);

		return new SimpleIntegerProperty(periodo.getYears());
	}

	public DoubleProperty divida() {

		return new SimpleDoubleProperty(this.divida.getValor());

	}

	public String toString() {
		return "ID: " + id.get() + " | DIVIDA: " + this.divida.getValor() + " | NOME: " + nome.get() + " | CPF: "
				+ cpf.get() + " | DATA NASCIMENTO: " + dataNascimento.get() + " | ENDERECO ID: " + endereco.getId();
	}

}
