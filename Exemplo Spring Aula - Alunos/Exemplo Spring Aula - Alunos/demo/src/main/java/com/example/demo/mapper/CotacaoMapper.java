package com.example.demo.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import com.example.demo.Entities.Cotacao;
import com.example.demo.dto.CotacaoDTO;

@Mapper(componentModel = "spring")
public interface CotacaoMapper {
    CotacaoDTO toDto(Cotacao cotacao);
    Cotacao toEntity(CotacaoDTO cotacaoDTO);
    List<CotacaoDTO> toDTOList(List<Cotacao> cotacao);
}