package com.volgsys.api.Controller;

import com.volgsys.api.model.dto.EmpresaDTO;
import com.volgsys.api.service.ServiceBrasilAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags = {"CNPJ"}, value = "Controller", description = "find informations by cnpj")
public class CNPJAPIController {

    @Autowired
    private final ServiceBrasilAPI serviceBrasilAPI;

    public CNPJAPIController(ServiceBrasilAPI serviceBrasilAPI) {
        this.serviceBrasilAPI = serviceBrasilAPI;
    }

    @GetMapping("/consult")
    @ApiOperation(value = "Dados CNPJ", response = EmpresaDTO.class, responseContainer = "dados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de bancos retornada com sucesso"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<EmpresaDTO> consultaCNPJ(@RequestParam(required = true) Long cnpj) {
        try {
            return ResponseEntity.ok(serviceBrasilAPI.buscaCNPJ(cnpj));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
