package com.volgsys.api.model.enums;

import lombok.Getter;

@Getter
public enum TipoUser {

    PF("CPF", "Pessoa Física"),
    PJ("CNPJ", "Pessoa Jurídica");

    private final String codigo;
    private final String descricao;

    TipoUser(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
}
