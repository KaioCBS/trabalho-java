package com.example.demo.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CotacaoDTO {
    @NotNull(message = "Cliente é obrigatório")
    private Long clienteid;
    @NotNull(message = "Destino é obrigatório")
    private Long destino_id;
    @NotNull(message = "A data da viagem é obrigatória")
    private LocalDate dataViagem;
    @NotNull(message = "A data de retorno é obrigatória")
    private LocalDate dataRetorno;
    @NotNull(message = "Informe o número de pessoas (obrigatório)")
    private Integer numeroDePessoas;
    @NotNull(message = "o valor total e obrigatorio")
    private Double ValorTotal; 

}