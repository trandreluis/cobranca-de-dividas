package edu.ifpb.monteiro.ads.model;

import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * 
 * @author Andre Luis
 * 
 *         e-mail: tr.andreluis@gmail.com
 *
 */

public class Parcela {

	private IntegerProperty id;
	private DoubleProperty valor;
	private ObjectProperty<LocalDate> dataParcela;
	private BooleanProperty paga;
	private BooleanProperty atrasada;

	public Parcela() {
		
	}

	public Parcela(double valor, LocalDate dataParcela) {

		this.valor = new SimpleDoubleProperty(valor);
		this.dataParcela = new SimpleObjectProperty<LocalDate>(dataParcela);
		this.paga = new SimpleBooleanProperty(false);
		this.atrasada = new SimpleBooleanProperty(false);
		
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

	public LocalDate getDataParcela() {
		return dataParcela.get();
	}

	public void setDataParcela(LocalDate dataParcela) {
		this.dataParcela = new SimpleObjectProperty<LocalDate>(dataParcela);
	}

	public ObjectProperty<LocalDate> dataDivida() {
		return this.dataParcela;
	}

	public String toString() {
		return "ID: "+this.id.get()+" | VALOR: "+this.valor.get() +" | DATA: "+this.dataParcela.get();
	}
	
}
