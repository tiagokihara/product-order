package br.com.tiagokihara.productorder.domain;

import java.math.BigDecimal;

import br.com.tiagokihara.productorder.model.Produto;

public class ProdutoItem {

	private String gtin;
	private String nome;
	private BigDecimal quantidade;
	private FornecedorItem fornecedorItem;

	public ProdutoItem(Produto produto, BigDecimal quantidade) {
		this.gtin = produto.getGtin();
		this.nome = produto.getNome();
		this.quantidade = quantidade;
	}

	public String getGtin() {
		return this.gtin;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public FornecedorItem getFornecedorItem() {
		return fornecedorItem;
	}

	public void setFornecedorItem(FornecedorItem fornecedorItem) {
		this.fornecedorItem = fornecedorItem;
	}

}
