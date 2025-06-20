package com.example.demo.service.Utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Entities.Desconto;
import com.example.demo.dto.DescontoDTO;
import com.example.demo.mapper.DescontoMapper;
import com.example.demo.repository.DescontoRepositorio;

@Service
public class DescontoService {

    @Autowired
    private DescontoRepositorio descontoRepositorio;

    @Autowired
    private DescontoMapper descontoMapper;

    public DescontoDTO registrar(DescontoDTO dto) {
        Desconto desconto = descontoMapper.toEntity(dto);
        desconto.setDataAplicacao(LocalDateTime.now());
        return descontoMapper.toDto(descontoRepositorio.save(desconto));
    }

    public List<DescontoDTO> listarTodos() {
        return descontoRepositorio.findAll()
                .stream()
                .map(descontoMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<DescontoDTO> buscarPorId(Long id) {
        return descontoRepositorio.findById(id).map(descontoMapper::toDto);
    }

    public List<DescontoDTO> listarPorCotacao(Long cotacaoId) {
        return descontoRepositorio.findByCotacaoId(cotacaoId)
                .stream()
                .map(descontoMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<DescontoDTO> atualizar(Long id, DescontoDTO dto) {
        return descontoRepositorio.findById(id).map(desconto -> {
            desconto.setValorDesconto(dto.getValorDesconto());
            desconto.setDescricao(dto.getDescricao());
            return descontoMapper.toDto(descontoRepositorio.save(desconto));
        });
    }

    public void remover(Long id) {
        descontoRepositorio.deleteById(id);
    }
}

