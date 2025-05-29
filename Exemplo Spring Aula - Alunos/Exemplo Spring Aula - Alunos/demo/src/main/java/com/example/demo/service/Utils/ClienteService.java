package com.example.demo.service.Utils;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Entities.Cliente;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.mapper.ClienteMapper;
import com.example.demo.repository.ClienteRepositorio;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private ClienteMapper clienteMapper;

    public Optional<ClienteDTO> buscarPorId(Long id) {
        return clienteRepositorio.findById(id).map(clienteMapper::toDTO);
    }

    public Optional<ClienteDTO> buscarPorEmail(String email) {
        return clienteRepositorio.findByEmail(email).map(clienteMapper::toDTO);
    }

    public ClienteDTO criarCliente(ClienteDTO dto) {
        Cliente cliente = clienteMapper.toEntity(dto);
        return clienteMapper.toDTO(clienteRepositorio.save(cliente));
    }

    public List<ClienteDTO> listarTodos() {
        return clienteRepositorio.findAll()
                .stream()
                .map(clienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ClienteDTO> atualizar(Long id, ClienteDTO dto) {
        return clienteRepositorio.findById(id).map(cliente -> {
            cliente.setNome(dto.getNome());
            cliente.setEmail(dto.getEmail());
            cliente.setTelefone(dto.getTelefone());
            cliente.setDocumento(dto.getDocumento());
            return clienteMapper.toDTO(clienteRepositorio.save(cliente));
        });
    }
}

