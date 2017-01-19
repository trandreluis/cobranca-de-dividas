package edu.ifpb.monteiro.ads.model;

import java.sql.Date;

/**
 * 
 * @author Andre Luis
 * 
 *         e-mail: tr.andreluis@gmail.com
 *
 */

public class Divida {
	
	private long id;
	private double valor;
	private Date dataDivida;
	private String descricao;
	
	public Divida(double valor, Date dataDivida, String descricao) {
		
		this.valor = valor;
		this.dataDivida = dataDivida;
		this.descricao = descricao;
		
	}
	
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Date getDataDivida() {
		return dataDivida;
	}
	public void setDataDivida(Date data_divida) {
		this.dataDivida = data_divida;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
