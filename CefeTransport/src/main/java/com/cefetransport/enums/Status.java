package com.cefetransport.enums;

public enum Status {
    
    FUNCIONAL("Funcional"),
    MANUTENCAO("Manutenção");

    private String status;

    private Status(String status) {

        this.status = status;

    }

}