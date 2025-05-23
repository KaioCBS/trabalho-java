package com.example.demo.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CotacaoDTO {
    private Long clienteId;
    private Long destinoId;

    @NotBlank(message = "a data da viagem é obrigatorio")
    private LocalDate dataViagem;
    @NotBlank(message = "data de retorno é obrigatorio")
    private LocalDate dataRetorno;
    @NotBlank(message = "informe o numero de pessoas (obrigatorio)")
    private int numeroPessoas;
}