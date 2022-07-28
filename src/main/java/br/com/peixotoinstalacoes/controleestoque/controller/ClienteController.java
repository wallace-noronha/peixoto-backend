package br.com.peixotoinstalacoes.controleestoque.controller;

import br.com.peixotoinstalacoes.controleestoque.model.Cliente;
import br.com.peixotoinstalacoes.controleestoque.repository.ClienteRepository;
import br.com.peixotoinstalacoes.controleestoque.repository.EnderecoRepository;
import br.com.peixotoinstalacoes.controleestoque.service.HashGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController()
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteRepository clienteRepository;
    private HashGenerator hashGenerator;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository, HashGenerator hashGenerator) {
        this.clienteRepository = clienteRepository;
        this.hashGenerator = hashGenerator;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAll(){
        return ResponseEntity.ok(clienteRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> get(@PathVariable String id){
        if (clienteRepository.findById(id).isPresent()) {
            return ResponseEntity.ok(clienteRepository.findById(id).get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody Cliente cliente) {
        if (clienteRepository.findById(cliente.getId()).isPresent()){
            return ResponseEntity.ok(clienteRepository.save(cliente));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id){
        if (clienteRepository.findById(id).isPresent()){
            clienteRepository.deleteById(id);
            return ResponseEntity.ok("Deleted");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
