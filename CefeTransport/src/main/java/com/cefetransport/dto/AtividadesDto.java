package com.cefetransport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtividadesDto {

    private String funcionario;
    
    private String modal;
    
    private String atividade;

    private String dataHora;

}