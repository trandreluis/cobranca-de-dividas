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
	private Date data_divida;
	private String descricao;
	
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Date getData_divida() {
		return data_divida;
	}
	public void setData_divida(Date data_divida) {
		this.data_divida = data_divida;
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
