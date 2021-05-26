package br.com.tiagokihara.productorder.api.dto;

import br.com.tiagokihara.productorder.model.Fornecedor;

public class FornecedorDto {
	
	private String nome;
	
	public FornecedorDto(Fornecedor fornecedor) {
		this.nome = fornecedor.getNome();				
	}

	public String getNome() {
		return nome;
	}

}
