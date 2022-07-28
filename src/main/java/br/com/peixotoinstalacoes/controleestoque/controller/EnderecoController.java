package br.com.peixotoinstalacoes.controleestoque.controller;

import br.com.peixotoinstalacoes.controleestoque.model.Endereco;
import br.com.peixotoinstalacoes.controleestoque.repository.EnderecoRepository;
import br.com.peixotoinstalacoes.controleestoque.service.SearchCep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/endereco")
public class EnderecoController {

    private EnderecoRepository enderecoRepository;
    private SearchCep searchCep;

    @Autowired
    public EnderecoController(EnderecoRepository enderecoRepository, SearchCep searchCep){
        this.enderecoRepository = enderecoRepository;
        this.searchCep = searchCep;
    }

    @GetMapping()
    public ResponseEntity<List<Endereco>> getAll(){
        return ResponseEntity.ok(enderecoRepository.findAll());
    }

    @GetMapping("/cep/{cep}")
    public ResponseEntity<Endereco> getCep(@PathVariable String cep){
        return searchCep.getCep(cep);
    }

}
