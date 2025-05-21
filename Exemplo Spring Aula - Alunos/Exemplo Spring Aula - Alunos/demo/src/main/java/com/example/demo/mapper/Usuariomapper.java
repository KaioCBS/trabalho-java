package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import com.example.demo.Entities.Usuario;
import com.example.demo.dto.usuarioDTO;

@Mapper(componentModel = "spring")
public interface Usuariomapper {

usuarioDTO toDto(Usuario usuario);

Usuario toEntity(usuarioDTO UsuarioDTO);

List<usuarioDTO> toDTOList(List<Usuario> usuario);

}
