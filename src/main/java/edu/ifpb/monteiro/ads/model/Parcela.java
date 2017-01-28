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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author Andre Luis
 * 
 *         e-mail: tr.andreluis@gmail.com
 *
 */

public class Parcela {

	private IntegerProperty id;
	private Divida dividaParcela;
	private DoubleProperty valorParcela;
	private ObjectProperty<LocalDate> dataParcela;
	private BooleanProperty paga;
	private BooleanProperty atrasada;

	public Parcela() {

	}

	public Parcela(Divida divida, double valor, LocalDate dataParcela) {

		this.valorParcela = new SimpleDoubleProperty(valor);
		this.dataParcela = new SimpleObjectProperty<LocalDate>(dataParcela);
		this.dividaParcela = divida;
		this.paga = new SimpleBooleanProperty(false);
		this.atrasada = new SimpleBooleanProperty(false);

	}

	public Integer getId() {
		return id.get();
	}

	public void setId(Integer id) {
		this.id = new SimpleIntegerProperty(id);
	}

	public Double getValorParcela() {
		return valorParcela.get();
	}

	public void setValorParcela(Double valor) {
		this.valorParcela = new SimpleDoubleProperty(valor);
	}

	public LocalDate getDataParcela() {
		return dataParcela.get();
	}

	public void setDataParcela(LocalDate dataParcela) {
		this.dataParcela = new SimpleObjectProperty<LocalDate>(dataParcela);
	}

	public Divida getDividaParcela() {
		return dividaParcela;
	}

	public void setDividaParcela(Divida divida) {
		this.dividaParcela = divida;
	}

	public boolean getPaga() {
		return paga.get();
	}

	public void setPaga(boolean paga) {
		this.paga = new SimpleBooleanProperty(paga);
	}

	public boolean getAtrasada() {
		return atrasada.get();
	}

	public void setAtrasada(boolean atrasada) {
		this.atrasada = new SimpleBooleanProperty(atrasada);
	}
	
	public DoubleProperty valorParcela() {
		return this.valorParcela;
	}

	public ObjectProperty<LocalDate> dataParcela() {
		return this.dataParcela;
	}
	
	public StringProperty paga() {
		if(paga.get()) {
			return new SimpleStringProperty("SIM");
		}
		return new SimpleStringProperty("NAO");
	}
	
	public StringProperty atrasada() {
		if(atrasada.get()) {
			return new SimpleStringProperty("SIM");
		}
		return new SimpleStringProperty("NAO");
	}
	
	public String toString() {
		return "ID: " + this.id.get() + " | ID_DIVIDA: " + this.dividaParcela.getId() + " | VALOR: "
				+ this.valorParcela.get() + " | DATA: "+this.dataParcela.get().toString()
				+" | PAGA: "+this.paga.get()+" | ATRASADA: "+this.atrasada.get();
	}

}
