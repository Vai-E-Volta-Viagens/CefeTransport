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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "funcionarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Funcionario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O 'Nome' é obrigatório")
    @Size(min = 2, message = "O 'Nome' precisa ter no mínimo 2 caracteres")
    private String nome;

    @NotBlank(message = "O 'Sobrenome' é obrigatório")
    @Size(min = 2, message = "O 'Sobrenome' precisa ter no mínimo 2 caracteres")
    private String sobrenome;

    @NotBlank(message = "O 'E-mail' é obrigatório")
    @Email(message = "Preencha o 'E-mail' corretamente")
    private String email;

    @NotBlank(message = "A 'Senha' é obrigatória")
    @Size(min = 3, message = "A 'Senha' preciso ter no mínimo 3 caracteres")
    private String senha;

}