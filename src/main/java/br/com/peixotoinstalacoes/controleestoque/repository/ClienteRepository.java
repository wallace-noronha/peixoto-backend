package br.com.peixotoinstalacoes.controleestoque.repository;

import br.com.peixotoinstalacoes.controleestoque.model.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends CrudRepository<Cliente, String> {

    Optional<Cliente> findById(String id);

    List<Cliente> findAll();

    Cliente save(Cliente cliente);

    Optional<Cliente> findByNumero(String cpf);

    void deleteById(String id);

}
