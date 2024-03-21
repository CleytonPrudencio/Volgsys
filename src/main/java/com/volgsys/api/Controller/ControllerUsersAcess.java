package com.volgsys.api.Controller;

import com.volgsys.api.UserApi;
import com.volgsys.api.model.ListUser;
import com.volgsys.api.model.dto.UserDTO;
import com.volgsys.api.service.ServiceUser;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags = {"Users"}, value = "Controller", description = "Users")
public class ControllerUsersAcess implements UserApi {

    @Autowired
    private ServiceUser serviceUser;

    @Override
    public Mono<ResponseEntity<String>> setUser(String nomeCompleto, String cpfCnpj, String email, String telefone, String dataDeNascimento, String cep, String password, ServerWebExchange exchange) {

        UserDTO userDTO = new UserDTO();
        userDTO.setNome(nomeCompleto);
        userDTO.setCpfcnpj(cpfCnpj.replaceAll("[^0-9]", ""));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dataDeNascimento,formatter);
        userDTO.setDtNascimento(date);
        userDTO.setCep(cep);
        userDTO.setEmail(email);
        userDTO.setTelefone(telefone);
        userDTO.setPassword(password);

        try {
            return Mono.just(ResponseEntity.ok(serviceUser.saveUser(userDTO)));
        } catch (Exception e) {
            return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()));
        }
    }

    @Override
    public Mono<ResponseEntity<ListUser>> listUser(String cpfcnpj, ServerWebExchange exchange) {
        try {
            return Mono.just(ResponseEntity.ok(serviceUser.listUser(cpfcnpj)));
        } catch (IllegalArgumentException e) {
            return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null  ));
        }
    }
}
