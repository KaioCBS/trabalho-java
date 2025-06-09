package com.example.demo.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.example.demo.Entities.Cotacao;
import com.example.demo.dto.CotacaoDTO;
@Mapper(componentModel = "spring", uses = {ClienteMapper.class, DestinoMapper.class})
public interface CotacaoMapper {
    
    // Mapper gerado pelo MapStruct vai fazer a conversão automaticamente
    @Mapping(target = "dataCotacao", ignore = true) // dataCotacao será setada no service, não no DTO
    Cotacao toEntity(CotacaoDTO cotacaoDTO);
    CotacaoDTO toDto(Cotacao cotacao);
    List<CotacaoDTO> toDTOList(List<Cotacao> cotacao);
}