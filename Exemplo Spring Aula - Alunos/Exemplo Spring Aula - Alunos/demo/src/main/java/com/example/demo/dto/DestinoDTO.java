package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DestinoDTO {
    private Long id;
    
    private String descricao;

    private Double precoPorPessoa;

    private String nome;

    private String localizacao;
}

