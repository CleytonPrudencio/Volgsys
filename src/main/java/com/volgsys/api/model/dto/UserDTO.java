package com.volgsys.api.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@Setter
public class UserDTO {

    private String nome;
    private String cpfcnpj;
    private LocalDate dtNascimento;
    private String rua;
    private String cep;
    private String email;
    private String telefone;
    private String TipoUser;
    private String password;

}
