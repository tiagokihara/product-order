package br.com.tiagokihara.productorder.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tiagokihara.productorder.model.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, String> {
}
