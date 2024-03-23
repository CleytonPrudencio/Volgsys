package com.volgsys.api.model.dto;

import java.util.List;

public class BankListDTO {
    private List<BankDTO> banks;

    // Getters e Setters

    public List<BankDTO> getBanks() {
        return banks;
    }

    public void setBanks(List<BankDTO> banks) {
        this.banks = banks;
    }
}

