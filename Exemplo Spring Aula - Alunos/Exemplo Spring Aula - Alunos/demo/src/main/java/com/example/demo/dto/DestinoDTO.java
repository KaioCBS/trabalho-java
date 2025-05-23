package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DestinoDTO {
    private Long id;
    private String descricao;
    private float precoPorPessoa;

    @NotBlank(message = "o nome é obrigatorio")
    private String nome;

    @NotBlank(message = "a localização é obrigatorio")
    private String localizacao;
}

