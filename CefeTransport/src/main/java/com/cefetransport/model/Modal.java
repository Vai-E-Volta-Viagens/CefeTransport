package com.cefetransport.model;

import com.cefetransport.enums.Status;
import com.cefetransport.enums.Tipo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "modais")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Modal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Insira o 'Tipo' do modal")
    @Size(min = 2, message = "O campo 'Tipo' precisa ter no mínimo 2 caracteres")
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @NotBlank(message = "Insira o 'Registro' do modal")
    @Size(min = 7, message = "O 'Registro' precisa ter no mínimo 7 caracteres")
    private String registro;

    @NotBlank(message = "Insira a quantidade de 'Vagas' do modal")
    @Size(min = 8, message = "O veículo precisa ter no mínimo 8 'Vagas'")
    private Integer vagas;

    @NotBlank(message = "Selecione o 'Status' atual do modal")
    @Enumerated(EnumType.STRING)
    private Status status;

}   