package com.example.demo.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import com.example.demo.Entities.Pagamento;
import com.example.demo.dto.PagamentoDTO;

@Mapper(componentModel = "spring")
public interface Pagamentomapper {
    PagamentoDTO toDto(Pagamento pagamento);
    Pagamento toEntity(PagamentoDTO pagamentoDTO);
    List<PagamentoDTO> toDTOList(List<Pagamento> pagamento);
}