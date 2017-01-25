package edu.ifpb.monteiro.ads.model;

import java.time.LocalDate;

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

public class Divida {

	private IntegerProperty id;
	private DoubleProperty valor;
	private ObjectProperty<LocalDate> dataDivida;
	private StringProperty descricao;

	public Divida() {
		
	}

	public Divida(double valor, LocalDate dataDivida, String descricao) {

		this.valor = new SimpleDoubleProperty(valor);
		this.dataDivida = new SimpleObjectProperty<LocalDate>(dataDivida);
		this.descricao = new SimpleStringProperty(descricao);

	}

	public Integer getId() {
		return id.get();
	}

	public void setId(Integer id) {
		this.id = new SimpleIntegerProperty(id);
	}

	public Double getValor() {
		return valor.get();
	}

	public void setValor(Double valor) {
		this.valor = new SimpleDoubleProperty(valor);
	}

	public LocalDate getDataDivida() {
		return dataDivida.get();
	}

	public void setDataDivida(LocalDate dataDivida) {
		this.dataDivida = new SimpleObjectProperty<LocalDate>(dataDivida);
	}

	public String getDescricao() {
		return descricao.get();
	}

	public void setDescricao(String descricao) {
		this.descricao = new SimpleStringProperty(descricao);
	}
	
	public ObjectProperty<LocalDate> dataDivida() {
		return this.dataDivida;
	}

	public String toString() {
		return "ID: "+this.id.get()+" | VALOR: "+this.valor.get() +" | DATA: "+this.dataDivida.get()+" | DESCRICAO: "+this.descricao.get();
	}
	
}
