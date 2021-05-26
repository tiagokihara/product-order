package br.com.tiagokihara.productorder.api.form;

import java.math.BigDecimal;

public class ProdutoForm {

	private String gtin;
	private BigDecimal quantidade;

	public ProdutoForm() {
	}

	public ProdutoForm(String gtin, BigDecimal quantidade) {
		this.gtin = gtin;
		this.quantidade = quantidade;
	}

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

}
