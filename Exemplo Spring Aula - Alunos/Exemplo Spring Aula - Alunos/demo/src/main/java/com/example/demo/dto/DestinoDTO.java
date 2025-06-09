package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DestinoDTO {
    private Long id;
    
   @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotNull(message = "Preço por pessoa é obrigatório")
    @Positive(message = "Preço deve ser positivo")
    private Double precoPorPessoa;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    
    @NotBlank(message = "Localização é obrigatória")
    private String localizacao;
}

