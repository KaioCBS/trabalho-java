package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
<<<<<<< HEAD
import jakarta.validation.constraints.Positive;
=======
>>>>>>> 944e750ea295c808f1837f2ee5d96a6be2d53685
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DestinoDTO {

    private Long id;
<<<<<<< HEAD
    
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
=======

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;
>>>>>>> 944e750ea295c808f1837f2ee5d96a6be2d53685

    @NotBlank(message = "A localização é obrigatória")
    private String localizacao;

    @NotNull(message = "O preço por pessoa é obrigatório")
    private Double precoPorPessoa;
}