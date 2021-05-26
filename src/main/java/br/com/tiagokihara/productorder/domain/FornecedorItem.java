package br.com.tiagokihara.productorder.domain;

import java.math.BigDecimal;

public class FornecedorItem {

	private String cnpj;
	private String nome;
	private BigDecimal melhorPrecoPorQuantidade;

	public FornecedorItem(String cnpj, String nome, BigDecimal melhorPrecoPorQuantidade) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.melhorPrecoPorQuantidade = melhorPrecoPorQuantidade;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getMelhorPrecoPorQuantidade() {
		return melhorPrecoPorQuantidade;
	}
}
