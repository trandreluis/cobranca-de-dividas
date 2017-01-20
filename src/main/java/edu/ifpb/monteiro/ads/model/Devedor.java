package edu.ifpb.monteiro.ads.model;

import java.sql.Date;
import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
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

	public Devedor(Divida divida, String nome, String cpf, Date dataNascimento, Endereco endereco) {

		this.divida = divida;
		this.nome = new SimpleStringProperty(nome);
		this.cpf = new SimpleStringProperty(cpf);
		this.dataNascimento = new SimpleObjectProperty<LocalDate>(
				LocalDate.of(dataNascimento.getYear(), dataNascimento.getMonth(), dataNascimento.getDay()));
		this.endereco = endereco;

	}

	public Integer getId() {
		return id.get();
	}

	public void setId(Integer id) {
		this.id = new SimpleIntegerProperty(id);
	}

	public Divida getDivida() {
		return divida;
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
		return cpf.get();
	}

	public void setCpf(String cpf) {
		this.cpf = new SimpleStringProperty(cpf);
	}

	public LocalDate getDataNascimento() {
		return dataNascimento.get();
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = new SimpleObjectProperty<LocalDate>(dataNascimento);
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public StringProperty nome() {
		return nome;
	}

	public String toString() {
		return "ID: " + id + " | DIVIDA: " + divida.getValor() + " | NOME: " + nome + " | CPF: " + cpf
				+ " | DATA NASCIMENTO: " + dataNascimento + " | ENDERECO: " + endereco;
	}

}
