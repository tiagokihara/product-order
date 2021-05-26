package br.com.tiagokihara.productorder.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private Fornecedor fornecedor;
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<PedidoItem> itens = new ArrayList<>();
	private BigDecimal valorTotalPedido;

	public Pedido() {
	}

	public Pedido(Fornecedor fornecedor, PedidoItem pedidoItem) {
		this.fornecedor = fornecedor;
		adicionarPedidoItem(pedidoItem);
	}

	public void adicionarPedidoItem(PedidoItem pedidoItem) {
		this.itens.add(pedidoItem);
		this.valorTotalPedido = this.itens.stream().reduce(BigDecimal.ZERO, (partial, item) -> item.getTotal().add(partial),
				BigDecimal::add);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<PedidoItem> getItens() {
		return itens;
	}

	public void setItens(List<PedidoItem> itens) {
		this.itens = itens;
	}

	public BigDecimal getValorTotalPedido() {

		return valorTotalPedido;
	}

	public void setValorTotalPedido(BigDecimal valorTotalPedido) {
		this.valorTotalPedido = valorTotalPedido;
	}

}
