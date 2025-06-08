package com.example.demo.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import com.example.demo.Entities.Destino;
import com.example.demo.dto.DestinoDTO;

@Mapper(componentModel = "spring")
public interface DestinoMapper {
    DestinoDTO toDto(Destino destinoAtualizado);
    Destino toEntity(DestinoDTO destinoDTO);
    List<DestinoDTO> toDTOList(List<Destino> destino);
}

