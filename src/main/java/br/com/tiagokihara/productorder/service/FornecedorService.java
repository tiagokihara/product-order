package br.com.tiagokihara.productorder.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.tiagokihara.productorder.api.BusinessException;
import br.com.tiagokihara.productorder.domain.FornecedorItem;
import br.com.tiagokihara.productorder.domain.FornecedorPreco;
import br.com.tiagokihara.productorder.domain.Preco;
import br.com.tiagokihara.productorder.domain.ProdutoItem;

@Service
public class FornecedorService {

	public FornecedorItem definirMelhorFornecedor(List<FornecedorPreco> fornecedores, ProdutoItem produtoItem) {
		if (fornecedores == null || fornecedores.size() == 0) {
			throw new IllegalArgumentException("Nenhum fornecedor foi encontrado");
		}

		FornecedorItem fornecedorItem = null;

		for (FornecedorPreco fornecedor : fornecedores) {
			for (Preco preco : fornecedor.getPrecos()) {

				if (produtoItem.getQuantidade().compareTo(preco.getQuantidadeMinima()) >= 0) {
					if (fornecedorItem == null) {
						fornecedorItem = new FornecedorItem(fornecedor.getCnpj(), fornecedor.getNome(), preco.getPreco());
					} else if (fornecedorItem.getMelhorPrecoPorQuantidade().compareTo(preco.getPreco()) > 0) {
						fornecedorItem = new FornecedorItem(fornecedor.getCnpj(), fornecedor.getNome(), preco.getPreco());
					}
				}
			}
		}

		if (fornecedorItem == null) {
			throw new BusinessException(
					"Não foram encontrados fornecedores que atendam a quantidade mínima informada para o produto: "
							+ produtoItem.getNome());
		}

		return fornecedorItem;
	}

	public List<FornecedorPreco> recuperarFornecedoresPorProduto(String url, ProdutoItem produtoItem) {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<FornecedorPreco[]> responseEntity = restTemplate.getForEntity(url + produtoItem.getGtin(),
				FornecedorPreco[].class);

		return Arrays.asList(responseEntity.getBody());
	}

}
