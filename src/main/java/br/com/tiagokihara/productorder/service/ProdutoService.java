package br.com.tiagokihara.productorder.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.tiagokihara.productorder.api.form.ProdutoForm;
import br.com.tiagokihara.productorder.domain.ProdutoItem;
import br.com.tiagokihara.productorder.model.Produto;
import br.com.tiagokihara.productorder.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	public List<ProdutoForm> filtrarProdutosForm(List<ProdutoForm> produtosForm) {
		if (produtosForm == null || produtosForm.size() == 0) {
			throw new IllegalArgumentException("Não é possível gerar um pedido sem produtos.");
		}

		produtosForm = filtrarPedidosComQtdMaiorQueZero(produtosForm);
		if (produtosForm.size() == 0) {
			throw new IllegalArgumentException("Não é possível gerar um pedido sem produtos.");
		}
		
		return produtosForm;
	}

	public List<ProdutoItem> carregarProdutosItens(List<ProdutoForm> produtosForm,
			ProdutoRepository produtoRepository) {
		
		List<ProdutoItem> produtosItens = new ArrayList<>();
		produtosForm.forEach(
				p -> produtosItens.add(new ProdutoItem(getProdutoByGtin(p.getGtin(), produtoRepository), p.getQuantidade())));

		return produtosItens;
	}

	private List<ProdutoForm> filtrarPedidosComQtdMaiorQueZero(List<ProdutoForm> produtosForm) {
		return produtosForm.stream().filter(p -> !p.getQuantidade().equals(BigDecimal.ZERO))
				.collect(Collectors.toList());
	}
	
	private Produto getProdutoByGtin(String gtin, ProdutoRepository produtoRepository) {		
		return produtoRepository.findById(gtin).orElseThrow(IllegalArgumentException::new);
	}

}
