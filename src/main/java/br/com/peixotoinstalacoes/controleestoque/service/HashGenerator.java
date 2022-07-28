package br.com.peixotoinstalacoes.controleestoque.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class HashGenerator {

    public String generateMD5(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json);
        String result = Hashing.sha256().hashString(json, StandardCharsets.UTF_8).toString();
        return result;
    }

}
