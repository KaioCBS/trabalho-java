package com.example.demo.service.Utils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Cliente;
import com.example.demo.Entities.Destino;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.DestinoDTO;
import com.example.demo.mapper.DestinoMapper;
import com.example.demo.repository.DestinoRepositorio;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class DestinoService {

    @Autowired
    private DestinoRepositorio destinoRepositorio;

    @Autowired
    private DestinoMapper destinoMapper;

    
    public DestinoDTO salvar(DestinoDTO destinoDTO) {
        Destino destino = destinoMapper.toEntity(destinoDTO);
        Destino destinoSalvo = destinoRepositorio.save(destino);
        return destinoMapper.toDto(destinoSalvo);
    }

    public List<DestinoDTO> listarTodosDes() {
        List<Destino> destinos = destinoRepositorio.findAll();
        return destinoMapper.toDTOList(destinos);
    }

    public DestinoDTO buscarDestinoPorId(Long id) {
        Destino destino = destinoRepositorio.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Destino com ID " + id + " não encontrado"));
        return destinoMapper.toDto(destino);
    }

    public DestinoDTO atualizarDestino(Long id, DestinoDTO destinoDTO) {
        Destino destinoExistente = destinoRepositorio.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Destino com ID " + id + " não encontrado"));

        // Atualiza os dados do cliente
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