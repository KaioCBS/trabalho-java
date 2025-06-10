// Arquivo: CotacaoService.java
package com.example.demo.service.Utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Cotacao;
import com.example.demo.Entities.Destino;
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

        cotacao.setCliente(clienteRepositorio.findById(dto.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado.")));
        
        Destino destino = destinoRepositorio.findById(dto.getDestinoId())
                .orElseThrow(() -> new IllegalArgumentException("Destino não encontrado."));
        cotacao.setDestino(destino);

        cotacao.setDataCotacao(LocalDateTime.now());
        cotacao.setDataViagem(dto.getDataViagem());
        cotacao.setDataRetorno(dto.getDataRetorno());
        cotacao.setNumeroDePessoas(dto.getNumeroDePessoas());
        
        cotacao.setStatus("ATIVO");

        if (destino.getPrecoPorPessoa() != null && cotacao.getNumeroDePessoas() != null) {
            cotacao.setValorTotal(destino.getPrecoPorPessoa() * cotacao.getNumeroDePessoas());
        } else {
            throw new IllegalArgumentException("Não foi possível calcular o Valor Total. Preço por pessoa ou número de pessoas ausente.");
        }


        double preco = destino.getPrecoPorPessoa().doubleValue(); 
        int numPessoas = cotacao.getNumeroDePessoas().intValue(); 
        
        // Multiplicação e atribuição explícita para Float
        cotacao.setValorTotal((Double) (preco * numPessoas)); 
        
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