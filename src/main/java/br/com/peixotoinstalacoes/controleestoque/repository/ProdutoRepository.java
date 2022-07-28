package br.com.peixotoinstalacoes.controleestoque.repository;

import br.com.peixotoinstalacoes.controleestoque.model.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, String> {

    Optional<Produto> findById(String id);

    List<Produto> findAll();

    Produto save(Produto produto);

    Optional<Produto> findByCodigo(Long codigo);

    void deleteById(String id);

}
