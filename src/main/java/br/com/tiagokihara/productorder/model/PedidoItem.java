package br.com.tiagokihara.productorder.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PedidoItem {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	@ManyToOne
	private Produto produto;
	private BigDecimal quantidade;
	private BigDecimal preco;
	
	public PedidoItem() {}

	public PedidoItem(Produto produto, BigDecimal quantidade, BigDecimal valor) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.preco = valor;
	}

	public BigDecimal getTotal() {
		return this.quantidade.multiply(this.preco);
	}

	public Produto getProduto() {
		return produto;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
