package com.example.demo.service.Utils;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Entities.Cotacao;
import com.example.demo.dto.CotacaoDTO;
import com.example.demo.mapper.CotacaoMapper;
import com.example.demo.repository.CotacaoRepositorio;

@Service
public class CotacaoService {

    @Autowired
    private CotacaoRepositorio cotacaoRepositorio;

    @Autowired
    private CotacaoMapper cotacaoMapper;

    public CotacaoDTO criar(CotacaoDTO dto) {
        Cotacao cotacao = cotacaoMapper.toEntity(dto);
        cotacao.setDataCotacao(LocalDateTime.now());
        return cotacaoMapper.toDTO(cotacaoRepositorio.save(cotacao));
    }

    public List<CotacaoDTO> listarTodos() {
        return cotacaoRepositorio.findAll()
                .stream()
                .map(cotacaoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CotacaoDTO> buscarPorId(Long id) {
        return cotacaoRepositorio.findById(id).map(cotacaoMapper::toDTO);
    }

    public Optional<CotacaoDTO> atualizarStatus(Long id, String status) {
        return cotacaoRepositorio.findById(id).map(cotacao -> {
            cotacao.setStatus(status);
            return cotacaoMapper.toDTO(cotacaoRepositorio.save(cotacao));
        });
    }

    public void remover(Long id) {
        cotacaoRepositorio.deleteById(id);
    }
}

