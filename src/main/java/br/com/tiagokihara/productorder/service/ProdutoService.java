package br.com.tiagokihara.productorder.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.tiagokihara.productorder.api.form.ProdutoForm;
import br.com.tiagokihara.productorder.domain.ProdutoItem;
import br.com.tiagokihara.productorder.model.Produto;
import br.com.tiagokihara.productorder.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	public List<ProdutoForm> filtrarProdutosForm(List<ProdutoForm> produtosForm) {
		
		var produtos = Optional.ofNullable(produtosForm)
				.orElseThrow(() -> new IllegalArgumentException("Não é possível gerar um pedido sem produtos"))
				.stream()
				.filter(produto -> produto.getQuantidade().compareTo(BigDecimal.ZERO) > 0)
				.collect(Collectors.toList());
		
		if (produtos.size() == 0) {
			throw new IllegalArgumentException("Não é possível gerar um pedido sem produtos.");
		}
		
		return produtos;
	}

	public List<ProdutoItem> carregarProdutosItens(List<ProdutoForm> produtosForm,
			ProdutoRepository produtoRepository) {
		
		return produtosForm.stream()
				.map(produto -> new ProdutoItem(getProdutoByGtin(produto.getGtin(), produtoRepository), produto.getQuantidade()))
				.collect(Collectors.toList());
	}
	
	private Produto getProdutoByGtin(String gtin, ProdutoRepository produtoRepository) {		
		return produtoRepository.findById(gtin).orElseThrow(IllegalArgumentException::new);
	}

}
