package br.com.tiagokihara.productorder.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Fornecedor {

	@Id
	private String cnpj;
	private String nome;

	public Fornecedor() {
	}

	public Fornecedor(String cnpj, String nome) {
		this.cnpj = cnpj;
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
