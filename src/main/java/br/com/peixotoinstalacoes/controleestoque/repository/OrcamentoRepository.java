package br.com.peixotoinstalacoes.controleestoque.repository;

import br.com.peixotoinstalacoes.controleestoque.model.Orcamento;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrcamentoRepository extends CrudRepository<Orcamento, String> {

    List<Orcamento> findAll();

    Orcamento save(Orcamento orcamento);

}
