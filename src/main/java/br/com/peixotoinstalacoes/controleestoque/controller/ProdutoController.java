package br.com.peixotoinstalacoes.controleestoque.controller;


import br.com.peixotoinstalacoes.controleestoque.model.Produto;
import br.com.peixotoinstalacoes.controleestoque.repository.ProdutoRepository;
import br.com.peixotoinstalacoes.controleestoque.service.HashGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoRepository produtoRepository;
    private HashGenerator hashGenerator;

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository, HashGenerator hashGenerator){
        this.produtoRepository = produtoRepository;
        this.hashGenerator = hashGenerator;
    }

    @GetMapping("")
    public ResponseEntity<List<Produto>> getAll(){
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> get(@PathVariable String id){
        if (produtoRepository.findById(id).isPresent()) {
            return ResponseEntity.ok(produtoRepository.findById(id).get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Produto> create(@RequestBody Produto produto){
        return ResponseEntity.ok(produtoRepository.save(produto));
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody Produto produto) {
        if (produtoRepository.findById(produto.getId()).isPresent()){
            return ResponseEntity.ok(produtoRepository.save(produto));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id){
        if (produtoRepository.findById(id).isPresent()){
            produtoRepository.deleteById(id);
            return ResponseEntity.ok("Deleted");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}

