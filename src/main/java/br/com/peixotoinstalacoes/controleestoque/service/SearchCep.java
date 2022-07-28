package br.com.peixotoinstalacoes.controleestoque.service;

import br.com.peixotoinstalacoes.controleestoque.model.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SearchCep {

    private String baseUrl;

    @Autowired
    public SearchCep(@Value("${correios.url}")String baseUrl){
        this.baseUrl = baseUrl;
    }

    public ResponseEntity<Endereco> getCep(String cep){
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Endereco> response = restTemplate.getForEntity(baseUrl.concat("/ws/" + cep + "/json"), Endereco.class);
            if (!response.getBody().getUf().isEmpty()){
                return ResponseEntity.ok(response.getBody());
            }else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
