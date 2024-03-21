package com.volgsys.api.service;

import com.volgsys.api.model.CepModel;
import com.volgsys.api.model.CepResponse;
import lombok.extern.log4j.Log4j2;
import lombok.var;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Log4j2
public class ServiceCEP {



    public CepResponse BuscaCep(String cep) throws Exception {

        RestTemplate restTemplate = new RestTemplate();


        final String baseurl = "https://viacep.com.br/ws/"+ cep + "/json/";

        HttpHeaders headers = new HttpHeaders();
        //headers.set("Content-Type", "application/json");
        //headers.set("access_key", acesskey);


        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            var response = restTemplate.exchange( baseurl, HttpMethod.GET, entity , CepResponse.class );
            return response.getBody();
        } catch (Exception e) {
            throw new Exception(String.format("Erro ao obter CEP.", e));
        }
    }
}
