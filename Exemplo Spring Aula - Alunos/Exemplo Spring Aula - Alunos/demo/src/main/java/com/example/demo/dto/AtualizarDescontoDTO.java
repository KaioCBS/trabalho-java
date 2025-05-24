package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AtualizarDescontoDTO {
    private Float valorDesconto;
    private String descricao;
    // valor total depois do cupon de desconto e a descriçao do desconto
}
