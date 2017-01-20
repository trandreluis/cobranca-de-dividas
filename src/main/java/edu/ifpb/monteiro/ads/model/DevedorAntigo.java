package edu.ifpb.monteiro.ads.model;

import java.sql.Date;

/**
 * 
 * @author Andre Luis
 * 
 *         e-mail: tr.andreluis@gmail.com
 *
 */

public class DevedorAntigo {

	private long id;
	private Divida divida;
	private String nome;
	private String cpf;
	private Date dataNascimento;
	private Endereco endereco;

	public DevedorAntigo(Divida divida, String nome, String cpf, Date dataNascimento, Endereco endereco) {
		
		this.divida = divida;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		
	}
	
	public DevedorAntigo() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Divida getDivida() {
		return divida;
	}

	public void setDivida(Divida divida) {
		this.divida = divida;
	}

	public String toString() {
//		return "ID: " + id + " | ID_DIVIDA: " + divida.getId() + " | NOME: " + nome + " | CPF: " + cpf
//				+ " | DATA NASCIMENTO: " + dataNascimento.toString() + " | ENDERECO: " + endereco.getId();
		
		return "ID: " + id + " | NOME: " + nome + " | CPF: " + cpf
				+ " | DATA NASCIMENTO: " + dataNascimento.toLocaleString();
	
	}

}
