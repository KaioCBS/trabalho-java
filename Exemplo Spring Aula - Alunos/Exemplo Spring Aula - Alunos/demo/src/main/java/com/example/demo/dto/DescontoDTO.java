package com.example.demo.dto;

import lombok.Data;
import jakarta.validation.constraints.Size;

@Data
public class DescontoDTO {
    private Long cotacaoId;
    private Float valorDesconto;
    private String descricao;

    @Size(min= 5, max = 5, message = "Cupon de desconto neceesario") // fazer a validaçao dps 
    private String cupon;
}
