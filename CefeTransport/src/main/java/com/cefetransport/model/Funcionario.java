package com.cefetransport.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "funcionario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "O Nome é obrigatório")
    @Size(min = 2, message = "O Nome preciso ter no mínimo 2 caracteres")
    private String nome;

    @NotBlank(message = "O E-mail é obrigatório")
    @Email
    private String email;

    @NotBlank(message = "A Senha é obrigatória")
    @Size(min = 2, message = "O Nome preciso ter no mínimo 2 caracteres")
    private String senha;

}