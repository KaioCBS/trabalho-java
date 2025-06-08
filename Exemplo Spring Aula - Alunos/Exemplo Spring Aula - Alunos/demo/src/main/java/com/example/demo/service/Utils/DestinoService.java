package com.example.demo.service.Utils;

import com.example.demo.dto.DestinoDTO;
import com.example.demo.Entities.Destino;
import com.example.demo.mapper.DestinoMapper;
import com.example.demo.repository.DestinoRepositorio;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DestinoService {

    private final DestinoRepositorio destinoRepositorio;
    private final DestinoMapper destinoMapper;

    public DestinoDTO salvar(DestinoDTO destinoDTO) {
        Destino destino = destinoMapper.toEntity(destinoDTO);
        Destino destinoSalvo = destinoRepositorio.save(destino);
        return destinoMapper.toDto(destinoSalvo);
    }

    public List<DestinoDTO> listarTodos() {
        List<Destino> destinos = destinoRepositorio.findAll();
        return destinoMapper.toDTOList(destinos);
    }

    public DestinoDTO buscarPorId(Long id) {
        Destino destino = destinoRepositorio.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Destino com ID " + id + " não encontrado"));
        return destinoMapper.toDto(destino);
    }

    public DestinoDTO atualizar(Long id, DestinoDTO destinoDTO) {
        Destino destinoExistente = destinoRepositorio.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Destino com ID " + id + " não encontrado"));

        // Atualiza os dados do destino
        destinoExistente.setNome(destinoDTO.getNome());
        destinoExistente.setDescricao(destinoDTO.getDescricao());
        destinoExistente.setLocalizacao(destinoDTO.getLocalizacao());
        destinoExistente.setPrecoPorPessoa(destinoDTO.getPrecoPorPessoa());

        Destino destinoAtualizado = destinoRepositorio.save(destinoExistente);
        return destinoMapper.toDto(destinoAtualizado);
    }

    public void deletar(Long id) {
        if (!destinoRepositorio.existsById(id)) {
            throw new EntityNotFoundException("Destino com ID " + id + " não encontrado");
        }
        destinoRepositorio.deleteById(id);
    }
}