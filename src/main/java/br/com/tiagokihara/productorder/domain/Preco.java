package br.com.tiagokihara.productorder.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Preco {

	private BigDecimal preco;
	@JsonProperty(value = "quantidade_minima")
	private BigDecimal quantidadeMinima;

	public Preco() {
	}

	public Preco(BigDecimal preco, BigDecimal quantidadeMinima) {
		this.preco = preco;
		this.quantidadeMinima = quantidadeMinima;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public BigDecimal getQuantidadeMinima() {
		return quantidadeMinima;
	}

	public void setQuantidadeMinima(BigDecimal quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}
}
