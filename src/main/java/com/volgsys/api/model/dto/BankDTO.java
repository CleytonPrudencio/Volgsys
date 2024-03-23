package com.volgsys.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankDTO {

    @JsonProperty("ispb")
    private String ispb;

    @JsonProperty("name")
    private String name;

    @JsonProperty("code")
    private int code;

    @JsonProperty("fullName")
    private String fullName;

    // Construtores, getters e setters aqui...

    // Exemplo de construtor vazio
    public BankDTO() {
    }

    // Exemplo de construtor com todos os campos
    public BankDTO(String ispb, String name, int code, String fullName) {
        this.ispb = ispb;
        this.name = name;
        this.code = code;
        this.fullName = fullName;
    }

    // Getters e setters para todos os campos
    // Implemente-os conforme necessário
    public String getIspb() {
        return ispb;
    }

    public void setIspb(String ispb) {
        this.ispb = ispb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

