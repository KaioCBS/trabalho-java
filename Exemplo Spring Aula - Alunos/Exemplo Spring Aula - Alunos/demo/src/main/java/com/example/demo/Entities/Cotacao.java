// Arquivo: Cotacao.java
package com.example.demo.Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cotacao")
@NoArgsConstructor
@AllArgsConstructor
public class Cotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "destino_id", nullable = false)
    private Destino destino;

    @Column(name = "data_cotacao", nullable = false)
    private LocalDateTime dataCotacao;

    @Column(name = "data_viagem", nullable = false)
    private LocalDate dataViagem;

    @Column(name = "data_retorno", nullable = false)
    private LocalDate dataRetorno;

    @Column(name = "numero_de_pessoas", nullable = false)
    private Integer numeroDePessoas;

    @Column(nullable = false)
    private String status;

    @Column(name = "valor_total", nullable = false) 
    private Double valorTotal; 

}