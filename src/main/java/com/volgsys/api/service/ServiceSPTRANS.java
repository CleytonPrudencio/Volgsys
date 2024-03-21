package com.volgsys.api.service;

import com.volgsys.api.model.CepResponse;
import lombok.extern.log4j.Log4j2;
import lombok.var;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

@Service
@Log4j2
public class ServiceSPTRANS {

    private HttpEntity<String> entity;

    public String BuscaLinha(String linha) throws Exception {

        RestTemplate restTemplate = new RestTemplate();

        final String baseurl = "http://api.olhovivo.sptrans.com.br/v2.1/Login/Autenticar?token=75fb7b79fefad1ba92700cf08228098e91d7441410741a20620a9311d7ae0250";

        HttpHeaders headers = new HttpHeaders();
        //headers.set("Content-Type", "application/json");
        //headers.set("access_key", acesskey);


        HttpEntity<String> entityy = new HttpEntity<>(headers);
        try {
            var response = restTemplate.exchange( baseurl, HttpMethod.POST, entityy , String.class );
            headers.set("Cookie", response.getHeaders().get("Set-Cookie").stream().collect(Collectors.joining(";")));
            this.entity = new HttpEntity<String>(headers);
        } catch (Exception e) {
            throw new Exception(String.format("Erro ao obter token da url SPTRANS", e));
        }
        final String baseurllinha = "http://api.olhovivo.sptrans.com.br/v2.1/Linha/Buscar?termosBusca=" + linha;
        
        try {
            var response = restTemplate.exchange( baseurllinha, HttpMethod.GET, entity , String.class );
            return response.getBody();
        } catch (Exception e) {
            throw new Exception(String.format("Erro ao buscar linha.", e));
        }


    }
}
