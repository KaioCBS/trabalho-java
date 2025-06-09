package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CotacaoDTO {
    private ClienteDTO clienteDTO;
    private DestinoDTO destinoDTO;

    @NotBlank(message = "a data da viagem é obrigatorio")
    private LocalDate dataViagem;
    @NotBlank(message = "data de retorno é obrigatorio")
    private LocalDate dataRetorno;
    @NotBlank(message = "informe o numero de pessoas (obrigatorio)")
    private int numeroPessoas;
    
    @NotNull(message = "Valor é obrigatório")
    private Double valor;

     @NotNull(message = "Data da cotação é obrigatória")
    private LocalDateTime dataCotacao;

    private String status;
}