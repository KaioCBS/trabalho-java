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
@Table(name = "Pagamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cotacao_id", nullable = false)
    private Cotacao cotacao;

    @Column(nullable = false)
    private float valorPago;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private Date dataPagamento;

}
