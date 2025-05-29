package com.example.demo.dto;

public class DescontoDTO {

    private Long cotacaoId;
    private float valorDesconto;
    private String descricao;

    public Long getCotacaoId() {
        return cotacaoId;
    }

    public void setCotacaoId(Long cotacaoId) {
        this.cotacaoId = cotacaoId;
    }

    public float getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(float valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}