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

        cotacao.setCliente(clienteRepositorio.findById(dto.getClienteDTO().getId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado.")));
        
        Destino destino = destinoRepositorio.findById(dto.getDestinoDTO().getId())
                .orElseThrow(() -> new IllegalArgumentException("Destino não encontrado."));
        cotacao.setDestino(destino);

        cotacao.setDataCotacao(LocalDateTime.now());
        cotacao.setDataViagem(dto.getDataViagem());
        cotacao.setDataRetorno(dto.getDataRetorno());
        cotacao.setNumeroDePessoas(dto.getNumeroPessoas());
        
        cotacao.setStatus("ATIVO");

        // *************** Verificações e Cálculo do Valor Total (Revisado) *******************
        // A ordem das verificações é importante: primeiro se o destino foi encontrado,
        // depois se o precoPorPessoa do destino é válido.
        if (cotacao.getDestino() == null || cotacao.getDestino().getPrecoPorPessoa() == null) {
            throw new IllegalArgumentException("O destino ou o preço por pessoa do destino não pode ser nulo para calcular o valor total da cotação.");
        }
        
        if (cotacao.getNumeroDePessoas() == null || cotacao.getNumeroDePessoas() <= 0) {
            throw new IllegalArgumentException("Número de pessoas deve ser um valor positivo e não pode ser nulo para calcular o valor total da cotação.");
        }

        // Garanta que o cálculo seja feito com tipos primitivos e depois convertido
        // para o tipo wrapper Float se necessário, ou use Double por padrão.
        // Se precoPorPessoa é Double e numeroDePessoas é Integer:
        double preco = destino.getPrecoPorPessoa().doubleValue(); // Pega o valor primitivo double
        int numPessoas = cotacao.getNumeroDePessoas().intValue(); // Pega o valor primitivo int
        
        // Multiplicação e atribuição explícita para Float
        cotacao.setValorTotal((Double) (preco * numPessoas)); 
        // Ou, se a coluna for Double no DB e você quiser mais precisão:
        // cotacao.setValorTotal(preco * numPessoas); // Se valorTotal na entidade for Double
        // ************************************************************************************
        
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