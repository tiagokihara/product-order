package br.com.tiagokihara.productorder.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.tiagokihara.productorder.api.form.ProdutoForm;
import br.com.tiagokihara.productorder.domain.FornecedorPreco;
import br.com.tiagokihara.productorder.domain.ProdutoItem;
import br.com.tiagokihara.productorder.model.Fornecedor;
import br.com.tiagokihara.productorder.model.Pedido;
import br.com.tiagokihara.productorder.model.PedidoItem;
import br.com.tiagokihara.productorder.model.Produto;
import br.com.tiagokihara.productorder.repository.FornecedorRepository;
import br.com.tiagokihara.productorder.repository.PedidoRepository;
import br.com.tiagokihara.productorder.repository.ProdutoRepository;

@Service
public class PedidoService {

	@Value("${url.fornecedores}")
	private String urlFornecedores;
	private ProdutoRepository produtoRepository;
	private FornecedorRepository fornecedorRepository;
	private PedidoRepository pedidoRepository;
	private ProdutoService produtoService;
	private FornecedorService fornecedorService;

	public PedidoService() {
	}

	@Autowired
	public PedidoService(ProdutoRepository produtoRepository, FornecedorRepository fornecedorRepository,
			PedidoRepository pedidoRepository, ProdutoService produtoService, FornecedorService fornecedorService) {
		this.produtoRepository = produtoRepository;
		this.fornecedorRepository = fornecedorRepository;
		this.pedidoRepository = pedidoRepository;
		this.produtoService = produtoService;
		this.fornecedorService = fornecedorService;
	}

	@Transactional
	public List<Pedido> gerarPedidos(List<ProdutoForm> produtosForm) {
		produtosForm = produtoService.filtrarProdutosForm(produtosForm);
		List<ProdutoItem> produtosItens = produtoService.carregarProdutosItens(produtosForm, produtoRepository);

		produtosItens.forEach(produtoItem -> {
			List<FornecedorPreco> fornecedores = fornecedorService.recuperarFornecedoresPorProduto(urlFornecedores,
					produtoItem);
			produtoItem.setFornecedorItem(fornecedorService.definirMelhorFornecedor(fornecedores, produtoItem));
		});

		List<Pedido> pedidos = agruparPedidos(produtosItens);

		pedidos.forEach(p -> salvarPedido(p));

		return pedidos;
	}

	public List<Pedido> agruparPedidos(List<ProdutoItem> produtosItens) {

		Map<String, Pedido> pedidos = new HashMap<String, Pedido>();

		produtosItens.forEach(item -> {

			PedidoItem pedidoItem = new PedidoItem(new Produto(item.getGtin(), item.getNome()), item.getQuantidade(),
					item.getFornecedorItem().getMelhorPrecoPorQuantidade());

			if (pedidos.containsKey(item.getFornecedorItem().getCnpj())) {
				pedidoItem.setPedido(pedidos.get(item.getFornecedorItem().getCnpj()));
				pedidos.get(item.getFornecedorItem().getCnpj()).adicionarPedidoItem(pedidoItem);
			} else {
				Fornecedor fornecedor = new Fornecedor(item.getFornecedorItem().getCnpj(),
						item.getFornecedorItem().getNome());
				Pedido pedido = new Pedido(fornecedor, pedidoItem);
				pedidoItem.setPedido(pedido);
				pedidos.put(item.getFornecedorItem().getCnpj(), pedido);
			}
		});

		return new ArrayList<Pedido>(pedidos.values());
	}

	private Pedido salvarPedido(Pedido pedido) {
		pedido.setFornecedor(fornecedorRepository.save(pedido.getFornecedor()));
		return pedidoRepository.save(pedido);
	}
}
