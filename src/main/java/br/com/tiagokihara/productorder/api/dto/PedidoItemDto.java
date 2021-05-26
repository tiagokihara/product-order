package br.com.tiagokihara.productorder.api.dto;

import java.math.BigDecimal;

import br.com.tiagokihara.productorder.model.PedidoItem;

public class PedidoItemDto {

	private ProdutoDto produto;
	private BigDecimal quantidade;
	private BigDecimal preco;
	private BigDecimal total;

	public PedidoItemDto(PedidoItem pedidoItem) {
		this.produto = new ProdutoDto(pedidoItem.getProduto());
		this.quantidade = pedidoItem.getQuantidade();
		this.preco = pedidoItem.getPreco();
		this.total = this.quantidade.multiply(this.preco);
	}

	public ProdutoDto getProduto() {
		return produto;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}
	
	public BigDecimal getTotal() {
		return total;
	}

}
