package br.com.tiagokihara.productorder.api.dto;

import br.com.tiagokihara.productorder.model.Produto;

public class ProdutoDto {

	private String nome;

	public ProdutoDto(Produto produto) {
		this.nome = produto.getNome();
	}

	public String getNome() {
		return nome;
	}

}
