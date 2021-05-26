package br.com.tiagokihara.productorder.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tiagokihara.productorder.model.Pedido;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long>{

}
