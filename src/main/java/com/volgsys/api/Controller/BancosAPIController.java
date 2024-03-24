package com.volgsys.api.Controller;

import com.volgsys.api.model.dto.BankDTO;
import com.volgsys.api.service.ServiceBrasilAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags = {"Banks"}, value = "Controller", description = "find by banks")
public class BancosAPIController {

    @Autowired
    private final ServiceBrasilAPI serviceBrasilAPI;

    public BancosAPIController(ServiceBrasilAPI serviceBrasilAPI) {
        this.serviceBrasilAPI = serviceBrasilAPI;
    }

    @GetMapping("/list/bancos")
    @ApiOperation(value = "Lista de Bancos", response = BankDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de bancos retornada com sucesso"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<List<BankDTO>> listBancos() {
        try {
            var bancos = serviceBrasilAPI.listBancos(); // Método fictício para buscar os bancos

            return ResponseEntity.ok(bancos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/bancos")
    @ApiOperation(value = "Lista de Bancos", response = BankDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de bancos retornada com sucesso"),
            @ApiResponse(code = 500, message = "Erro interno no servidor")
    })
    public ResponseEntity<BankDTO> buscarBanco(@RequestParam(required = true) Integer idbanco) {
        try {
            BankDTO bancos = serviceBrasilAPI.buscaBancos(idbanco); // Método fictício para buscar os bancos

            return ResponseEntity.ok(bancos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
