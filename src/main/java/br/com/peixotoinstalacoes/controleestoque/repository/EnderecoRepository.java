package br.com.peixotoinstalacoes.controleestoque.repository;

import br.com.peixotoinstalacoes.controleestoque.model.Endereco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, String> {

    List<Endereco> findAll();

    Endereco save(Endereco cep);

    List<Endereco> findByCep(String cep);

    void deleteById(String id);

}
