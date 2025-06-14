package com.example.demo.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import com.example.demo.Entities.Cliente;
import com.example.demo.dto.ClienteDTO;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteDTO toDto(Cliente clienteAtualizado);
    Cliente toEntity(ClienteDTO clienteDTO);
    List<ClienteDTO> toDTOList(List<Cliente> cliente);
}