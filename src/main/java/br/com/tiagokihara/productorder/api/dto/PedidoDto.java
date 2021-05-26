package br.com.tiagokihara.productorder.api.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.tiagokihara.productorder.model.Pedido;

public class PedidoDto {

	private Long id;
	private FornecedorDto fornecedor;
	private List<PedidoItemDto> itens = new ArrayList<>();

	public PedidoDto(Pedido pedido) {
		this.id = pedido.getId();
		this.fornecedor = new FornecedorDto(pedido.getFornecedor());
		itens = pedido.getItens().stream().map(PedidoItemDto::new).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public FornecedorDto getFornecedor() {
		return fornecedor;
	}

	public List<PedidoItemDto> getItens() {
		return itens;
	}

}
