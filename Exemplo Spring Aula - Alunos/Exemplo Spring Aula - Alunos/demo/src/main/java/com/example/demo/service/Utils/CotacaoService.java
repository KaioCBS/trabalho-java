package com.example.demo.service.Utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Cotacao;
import com.example.demo.dto.CotacaoDTO;
import com.example.demo.mapper.CotacaoMapper;
import com.example.demo.repository.CotacaoRepositorio;
import com.example.demo.repository.ClienteRepositorio;
import com.example.demo.repository.DestinoRepositorio;

@Service
public class CotacaoService {

    @Autowired
    private CotacaoRepositorio cotacaoRepositorio;

    @Autowired
    private CotacaoMapper cotacaoMapper;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private DestinoRepositorio destinoRepositorio;

    public CotacaoDTO criar(CotacaoDTO dto) {
        Cotacao cotacao = cotacaoMapper.toEntity(dto);
        // Verifique se os IDs estão sendo passados corretamente
        cotacao.setCliente(clienteRepositorio.findById(dto.getClienteDTO().getId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado")));
        cotacao.setDestino(destinoRepositorio.findById(dto.getDestinoDTO().getId())
                .orElseThrow(() -> new IllegalArgumentException("Destino não encontrado")));
        // Definir as datas e outros campos obrigatórios
        cotacao.setDataCotacao(LocalDateTime.now());
        cotacao.setDataViagem(dto.getDataViagem());
        cotacao.setDataRetorno(dto.getDataRetorno());
        cotacao.setNumeroDePessoas(dto.getNumeroPessoas());
        // Status e valor total podem ser inicializados aqui, ajustar se desejar lógica
        // diferente
        cotacao.setStatus("ATIVO");
        cotacao.setValorTotal(cotacao.getDestino().getPrecoPorPessoa().floatValue() * cotacao.getNumeroDePessoas());
        Cotacao cotacaoSalva = cotacaoRepositorio.save(cotacao);
        return cotacaoMapper.toDto(cotacaoSalva);
    }

    public List<CotacaoDTO> listarTodos() {
        List<Cotacao> cotacoes = cotacaoRepositorio.findAll();
        return cotacaoMapper.toDTOList(cotacoes);
    }

    public Optional<CotacaoDTO> buscarPorId(Long id) {
        return cotacaoRepositorio.findById(id).map(cotacaoMapper::toDto);
    }

    public Optional<CotacaoDTO> atualizarStatus(Long id, String status) {
        return cotacaoRepositorio.findById(id).map(cotacao -> {
            cotacao.setStatus(status);
            return cotacaoMapper.toDto(cotacaoRepositorio.save(cotacao));
        });
    }

    public void remover(Long id) {
        cotacaoRepositorio.deleteById(id);
    }
}
