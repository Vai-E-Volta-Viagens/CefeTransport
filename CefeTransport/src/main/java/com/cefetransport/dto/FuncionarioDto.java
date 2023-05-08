package com.cefetransport.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDto {
    
    @NotBlank(message = "O E-mail é obrigatório")
    @Email
    private String email;

    @NotBlank(message = "A Senha é obrigatória")
    @Size(min = 2, message = "O Nome preciso ter no mínimo 2 caracteres")
    private String senha;

}