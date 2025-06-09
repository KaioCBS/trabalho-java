package com.example.demo.service.Utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Entities.Cotacao;
import com.example.demo.dto.CotacaoDTO;
import com.example.demo.mapper.CotacaoMapper;
import com.example.demo.repository.CotacaoRepositorio;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CotacaoService {

    @Autowired
    private CotacaoRepositorio cotacaoRepositorio;

    @Autowired
    private CotacaoMapper cotacaoMapper;

    public CotacaoDTO criarCotacao(CotacaoDTO cotacaoDTO) {
        Cotacao cotacao = cotacaoMapper.toEntity(cotacaoDTO);
        Cotacao novaCotacao = cotacaoRepositorio.save(cotacao);
        return cotacaoMapper.toDto(novaCotacao);
    }

    public List<CotacaoDTO> listarTodos() {
        return cotacaoRepositorio.findAll()
                .stream()
                .map(cotacaoMapper::toDto)
                .collect(Collectors.toList());
    }

    public CotacaoDTO buscarCotacaoPorId(Long id) {
        Cotacao cotacao = cotacaoRepositorio.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Cotação com ID " + id + " não encontrada"));
        return cotacaoMapper.toDto(cotacao);
    }

    public Optional<CotacaoDTO> atualizarStatus(Long id, String status) {
        return cotacaoRepositorio.findById(id).map(cotacao -> {
            cotacao.setStatus(status);
            return cotacaoMapper.toDto(cotacaoRepositorio.save(cotacao));
        });
    }

    public void remover(Long id) {
        cotacaoRepositorio.deleteById(id);
    }
}

