package com.example.demo.service.Utils;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Entities.Pagamento;
import com.example.demo.dto.PagamentoDTO;
import com.example.demo.mapper.PagamentoMapper;
import com.example.demo.repository.PagamentoRepositorio;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepositorio pagamentoRepositorio;

    @Autowired
    private PagamentoMapper pagamentoMapper;

    public PagamentoDTO registrar(PagamentoDTO dto) {
        Pagamento pagamento = pagamentoMapper.toEntity(dto);
        pagamento.setDataPagamento(LocalDateTime.now());
        return pagamentoMapper.toDto(pagamentoRepositorio.save(pagamento));
    }

    public Optional<PagamentoDTO> buscarPorId(Long id) {
        return pagamentoRepositorio.findById(id).map(pagamentoMapper::toDto);
    }

    public Optional<PagamentoDTO> atualizarStatus(Long id, String status) {
        return pagamentoRepositorio.findById(id).map(pagamento -> {
            pagamento.setStatus(status);
            return pagamentoMapper.toDto(pagamentoRepositorio.save(pagamento));
        });
    }

    public void remover(Long id) {
        pagamentoRepositorio.deleteById(id);
    }
}

