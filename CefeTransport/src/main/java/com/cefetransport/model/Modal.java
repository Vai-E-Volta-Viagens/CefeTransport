package com.cefetransport.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.cefetransport.enums.Status;
import com.cefetransport.enums.Tipo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.Size;
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
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Proprietaria proprietaria;

    @NotBlank(message = "Insira o 'Registro' do modal")
    @Size(min = 7, message = "O 'Registro' precisa ter no mínimo 7 caracteres")
    private String registro;

    // @NotBlank(message = "Insira a quantidade de 'Vagas' do modal")
    // @Size(min = 1, message = "O campo 'Vagas' precisar ter no mínimo 1 caracter")
    // private int vagas;

    @NotBlank(message = "Selecione o 'Status' atual do modal")
    @Enumerated(EnumType.STRING)
    private Status status;

}   