package com.example.demo.service.Utils;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.Entities.Cliente;
import com.example.demo.mapper.ClienteMapper;
import com.example.demo.repository.ClienteRepositorio;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepositorio clienteRepositorio;
    private final ClienteMapper clienteMapper;

    public ClienteDTO salvar(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente clienteSalvo = clienteRepositorio.save(cliente);
        return clienteMapper.toDto(clienteSalvo);
    }

    public List<ClienteDTO> listarTodos() {
        List<Cliente> clientes = clienteRepositorio.findAll();
        return clienteMapper.toDTOList(clientes);
    }

    public ClienteDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepositorio.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Cliente com ID " + id + " não encontrado"));
        return clienteMapper.toDto(cliente);
    }

    public ClienteDTO atualizar(Long id, ClienteDTO clienteDTO) {
        Cliente clienteExistente = clienteRepositorio.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Cliente com ID " + id + " não encontrado"));

        // Atualiza os dados do cliente
        clienteExistente.setNome(clienteDTO.getNome());
        clienteExistente.setEmail(clienteDTO.getEmail());
        clienteExistente.setTelefone(clienteDTO.getTelefone());
        clienteExistente.setDocumento(clienteDTO.getDocumento());

        Cliente clienteAtualizado = clienteRepositorio.save(clienteExistente);
        return clienteMapper.toDto(clienteAtualizado);
    }

    public void deletar(Long id) {
        if (!clienteRepositorio.existsById(id)) {
            throw new EntityNotFoundException("Cliente com ID " + id + " não encontrado");
        }
        clienteRepositorio.deleteById(id);
    }
}