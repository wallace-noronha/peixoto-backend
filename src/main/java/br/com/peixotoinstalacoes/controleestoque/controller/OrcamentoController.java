package br.com.peixotoinstalacoes.controleestoque.controller;

import br.com.peixotoinstalacoes.controleestoque.model.Orcamento;
import br.com.peixotoinstalacoes.controleestoque.repository.OrcamentoRepository;
import br.com.peixotoinstalacoes.controleestoque.service.GenerateReport;
import br.com.peixotoinstalacoes.controleestoque.service.HashGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController()
@RequestMapping("/orcamentos")
public class OrcamentoController {

    private OrcamentoRepository orcamentoRepository;
    private HashGenerator hashGenerator;
    private GenerateReport generateReport;


    @Autowired
    public OrcamentoController(OrcamentoRepository orcamentoRepository, HashGenerator hashGenerator, GenerateReport generateReport){
        this.orcamentoRepository = orcamentoRepository;
        this.hashGenerator = hashGenerator;
        this.generateReport = generateReport;
    }

    @GetMapping
    public ResponseEntity<List<Orcamento>> getAll(){
        return ResponseEntity.ok().body(orcamentoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orcamento> getOne(@PathVariable String id){
        if (orcamentoRepository.findById(id) != null) {
            return ResponseEntity.ok().body(orcamentoRepository.findById(id).get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Orcamento> save(@RequestBody Orcamento orcamento){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        orcamento.setData(LocalDateTime.now().format(formatter));
        return ResponseEntity.ok().body(orcamentoRepository.save(orcamento));
    }

    @PutMapping
    public ResponseEntity<Orcamento> update(@RequestBody Orcamento orcamento){
        if (orcamentoRepository.findById(orcamento.getId()).isPresent()){
            return ResponseEntity.ok().body(orcamentoRepository.save(orcamento));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id){
        if (orcamentoRepository.findById(id).isPresent()){
            orcamentoRepository.deleteById(id);
            return ResponseEntity.ok("deleted");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/report/{id}", produces = "application/pdf")
    public ResponseEntity<byte[]> generateReport(@PathVariable String id) throws InterruptedException {

        TimeUnit.SECONDS.sleep(5);
        if (orcamentoRepository.findById(id).isPresent()){
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            byte[] retorno = generateReport.generateReport(orcamentoRepository.findById(id).get());
            if (retorno != null){
                return new ResponseEntity<>(retorno, headers, HttpStatus.OK);
            }else {
                return ResponseEntity.badRequest().build();
            }
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
