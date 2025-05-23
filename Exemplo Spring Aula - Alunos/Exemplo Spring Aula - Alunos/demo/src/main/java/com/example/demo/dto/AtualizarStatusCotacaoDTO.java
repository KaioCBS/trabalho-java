package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

public class AtualizarStatusCotacaoDTO {
    private String status; //SE ESTÁ PENDENTE, SE FOI APROVADA, SE FOI REJEITADA
}