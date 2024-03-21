package com.volgsys.api.Repository;

import lombok.*;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "users")
public class UsuarioAcesso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String nome;

    @Column(name = "cpfcnpj", nullable = false)
    private String cpfcnpj;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "tipopessoa", nullable = false)
    private String tipopessoa;

    @Column(name = "email")
    private String email;

    @Column(name = "dtnascimento", nullable = false)
    private LocalDate dtNascimento;

    @Column(name = "endereco")
    private String rua;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "password", nullable = false)
    private String password;
}
