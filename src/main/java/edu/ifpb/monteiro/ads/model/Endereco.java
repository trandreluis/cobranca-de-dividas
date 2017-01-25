package edu.ifpb.monteiro.ads.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author Andre Luis
 * 
 *         e-mail: tr.andreluis@gmail.com
 *
 */

public class Endereco {

	private IntegerProperty id;
	private StringProperty rua;
	private StringProperty numero;
	private StringProperty bairro;
	private StringProperty cidade;
	private StringProperty estado;
	private StringProperty pontoReferencia;

	public Endereco() {

	}

	public Endereco(String rua, String numero, String bairro, String cidade, String estado, String pontoReferencia) {

		this.rua = new SimpleStringProperty(rua);
		this.numero = new SimpleStringProperty(numero);
		this.bairro = new SimpleStringProperty(bairro);
		this.cidade = new SimpleStringProperty(cidade);
		this.estado = new SimpleStringProperty(estado);
		this.pontoReferencia = new SimpleStringProperty(pontoReferencia);

	}

	public String getRua() {
		return rua.get();
	}

	public void setRua(String rua) {
		this.rua = new SimpleStringProperty(rua);
	}

	public String getNumero() {
		return numero.get();
	}

	public void setNumero(String numero) {
		this.numero = new SimpleStringProperty(numero);
	}

	public String getBairro() {
		return bairro.get();
	}

	public void setBairro(String bairro) {
		this.bairro = new SimpleStringProperty(bairro);
	}

	public String getCidade() {
		return cidade.get();
	}

	public void setCidade(String cidade) {
		this.cidade = new SimpleStringProperty(cidade);
	}

	public String getEstado() {
		return estado.get();
	}

	public void setEstado(String estado) {
		this.estado = new SimpleStringProperty(estado);
	}

	public String getPontoReferencia() {
		return pontoReferencia.get();
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = new SimpleStringProperty(pontoReferencia);
	}
	
	public StringProperty cidade() {
		return cidade;
	}

	public Integer getId() {
		return id.get();
	}

	public void setId(Integer id) {
		this.id = new SimpleIntegerProperty(id);
	}

	public String toString() {

		return "ID: "+this.id.get()+" | RUA: " + this.rua.get() + " | NUMERO: " + this.numero.get() + " | BAIRRO: " + this.bairro.get()
				+ " | CIDADE: " + this.cidade.get() + " | ESTADO: " + this.estado.get() + " | PONTO DE REFERENCIA: "
				+ this.pontoReferencia.get();

	}

}
