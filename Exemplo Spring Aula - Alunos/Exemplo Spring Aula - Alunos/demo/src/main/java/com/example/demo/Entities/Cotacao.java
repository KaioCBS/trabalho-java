package com.example.demo.Entities;

import java.sql.Date;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "Cotacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "destino_id", nullable = false)
    private Destino destino;

    @Column(nullable = false)
    private Date dataCotacao;
    @Column(nullable = false)
    private Integer numeroDePessoas;
    @Column(nullable = false)
    private float valorTotal;
    @Column(nullable = false)
    private String status;

}
