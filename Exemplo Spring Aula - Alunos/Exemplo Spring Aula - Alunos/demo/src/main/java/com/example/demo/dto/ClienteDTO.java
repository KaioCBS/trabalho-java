// Exemplo de ClienteDTO (se for o caso, ajuste para seu arquivo real)
package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ClienteDTO {
    private Long id; // Deve ser Long, não long
    @NotBlank(message = "o nome é obrigatorio")
    private String nome;
    @Size(min=11, max=11, message = "o cpf deve conter 11 caracteres")
    private String cpf;
    @Email(message = "E-mail inválido")
    private String email;
}