package com.cefetransport.enums;

public enum Tipo {
    
    VAN("Van"),
    TREM("Trem"),
    AVIAO("Avião"),
    BARCO("Barco"),
    METRO("Metrô"),
    ONIBUS("Ônibus");

    private String tipo;

    private Tipo(String tipo) {

        this.tipo = tipo;

    }

}