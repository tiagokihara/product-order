package br.com.tiagokihara.productorder.api;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tiagokihara.productorder.api.dto.PedidoDto;
import br.com.tiagokihara.productorder.api.form.ProdutoForm;
import br.com.tiagokihara.productorder.model.Pedido;
import br.com.tiagokihara.productorder.repository.PedidoRepository;
import br.com.tiagokihara.productorder.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
	
    private final PedidoService pedidoService;
    private final PedidoRepository pedidoRepository;
    
    @Autowired
	public PedidoController(PedidoService pedidoService, PedidoRepository pedidoRepository) {
    	this.pedidoService = pedidoService;
    	this.pedidoRepository = pedidoRepository;
	}
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    ResponseEntity<?> listarPedidos() {
    	
        Iterable<Pedido> pedidos = pedidoRepository.findAll();
        
        List<PedidoDto> pedidosDto = StreamSupport.stream(
                pedidos.spliterator(), false)
                .map(PedidoDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(pedidosDto);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    ResponseEntity<?> gerarPedidos(@RequestBody List<ProdutoForm> produtosForm) {
    	
        List<Pedido> pedidos = pedidoService.gerarPedidos(produtosForm);

        return ResponseEntity.ok(pedidos.stream().map(PedidoDto::new).collect(Collectors.toList()));
    }
}
