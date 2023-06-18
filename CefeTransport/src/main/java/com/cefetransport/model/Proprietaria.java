package com.cefetransport.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Proprietaria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Preencha o nome da empresa proprietária.")
    private String nome;

    @NotBlank(message = "Preencha o CNPJ da empresa proprietária.")
    @Size(min = 14, max = 18, message = "O CNPJ deve possuir no mínimo 14 caracteres e no máximo 18 caracteres.")
    private String cnpj;

}