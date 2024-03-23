package com.volgsys.api.Controller;


import com.volgsys.api.LinhasApi;
import com.volgsys.api.service.ServiceSPTRANS;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags = {"SpTrans"}, value = "Controller", description = "find by lines")
public class ControllerSPTRANS implements LinhasApi {

    @Autowired
    private ServiceSPTRANS serviceSPTRANS;


    @Override
    public Mono<ResponseEntity<String>> getLinhas(String linhaOuNome, ServerWebExchange exchange) {
        try {
            return Mono.just(ResponseEntity.ok(serviceSPTRANS.BuscaLinha(linhaOuNome)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
