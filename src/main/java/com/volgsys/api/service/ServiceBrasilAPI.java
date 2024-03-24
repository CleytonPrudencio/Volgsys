package com.volgsys.api.service;

import com.volgsys.api.model.dto.BankDTO;
import com.volgsys.api.model.dto.EmpresaDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class ServiceBrasilAPI {

    public List<BankDTO> listBancos() throws Exception {
        final String baseUrl = "https://brasilapi.com.br/api/banks/v1";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<BankDTO>> response = restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BankDTO>>() {}
        );

        return response.getBody();
    }

    public BankDTO buscaBancos(Integer banco) throws Exception {
        final String baseUrl = "https://brasilapi.com.br/api/banks/v1/" + banco;


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BankDTO> response = restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                BankDTO.class
        );

        return response.getBody();
    }

    public EmpresaDTO buscaCNPJ(Long cnpj) throws Exception {
        final String baseUrl = "https://brasilapi.com.br/api/cnpj/v1/" + cnpj;


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<EmpresaDTO> response = restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                EmpresaDTO.class
        );

        return response.getBody();
    }



}
