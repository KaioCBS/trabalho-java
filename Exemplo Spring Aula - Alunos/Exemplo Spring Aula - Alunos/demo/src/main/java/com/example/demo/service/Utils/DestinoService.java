package com.example.demo.service.Utils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Entities.Destino;
import com.example.demo.dto.DestinoDTO;
import com.example.demo.mapper.DestinoMapper;
import com.example.demo.repository.DestinoRepositorio;

@Service
public class DestinoService {

    @Autowired
    private DestinoRepositorio destinoRepositorio;

    @Autowired
    private DestinoMapper destinoMapper;

    public DestinoDTO criarDestino(DestinoDTO dto) {
        Destino destino = destinoMapper.toEntity(dto);
        return destinoMapper.toDto(destinoRepositorio.save(destino));
    }

    public List<DestinoDTO> listarTodos() {
        return destinoRepositorio.findAll()
                .stream()
                .map(destinoMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<DestinoDTO> buscarPorId(Long id) {
        return destinoRepositorio.findById(id).map(destinoMapper::toDto);
    }

    public Optional<DestinoDTO> atualizar(Long id, DestinoDTO dto) {
        return destinoRepositorio.findById(id).map(destino -> {
            destino.setNome(dto.getNome());
            destino.setDescricao(dto.getDescricao());
            destino.setLocalizacao(dto.getLocalizacao());
            destino.setPrecoPorPessoa(dto.getPrecoPorPessoa());
            return destinoMapper.toDto(destinoRepositorio.save(destino));
        });
    }

    public void remover(Long id) {
        destinoRepositorio.deleteById(id);
    }
}