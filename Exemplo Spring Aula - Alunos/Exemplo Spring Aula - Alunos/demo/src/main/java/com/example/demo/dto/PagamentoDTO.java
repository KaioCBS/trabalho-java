package com.example.demo.dto;


public class PagamentoDTO {

    private Long cotacaoId;
    private float valorPago;


    public Long getCotacaoId() {
        return cotacaoId;
    }

    public void setCotacaoId(Long cotacaoId) {
        this.cotacaoId = cotacaoId;
    }

    public float getValorPago() {
        return valorPago;
    }

    public void setValorPago(float valorPago) {
        this.valorPago = valorPago;
    }
}