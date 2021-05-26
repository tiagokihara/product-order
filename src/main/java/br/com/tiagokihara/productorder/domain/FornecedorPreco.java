package br.com.tiagokihara.productorder.domain;

import java.util.List;

public class FornecedorPreco {

	private String cnpj;
	private String nome;
	private List<Preco> precos;

	public FornecedorPreco() {
	}

	public FornecedorPreco(String cnpj, String nome) {
		this.cnpj = cnpj;
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<Preco> getPrecos() {
		return precos;
	}

	public void setPrecos(List<Preco> precos) {
		this.precos = precos;
	}
}
