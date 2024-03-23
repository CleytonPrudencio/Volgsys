package com.volgsys.api.Controller;


import com.volgsys.api.model.CepResponse;
import com.volgsys.api.CepApi;
import com.volgsys.api.service.ServiceCEP;
import io.swagger.annotations.Api;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags = {"Address"}, value = "Controller", description = "find by address")
public class ControllerCEP implements CepApi {

    @Autowired
    private ServiceCEP serviceCEP;


    @Override
    public Mono<ResponseEntity<CepResponse>> buscaCep(String cepId, ServerWebExchange exchange) {
        try {
            return Mono.just(ResponseEntity.ok(serviceCEP.BuscaCep(cepId)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
