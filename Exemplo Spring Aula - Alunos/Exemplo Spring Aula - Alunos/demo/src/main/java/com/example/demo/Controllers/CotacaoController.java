package com.example.demo.Controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CotacaoDTO;
import com.example.demo.service.Utils.CotacaoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Cotação", description = "Endpoints para gerenciamento de cotações")
@RestController
@RequestMapping("/api/cotacoes")
public class CotacaoController {

    @Autowired
    private CotacaoService cotacaoService;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody @Valid CotacaoDTO dto) {
        try {
            CotacaoDTO nova = cotacaoService.criar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nova);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace(); // Adicione esta linha para imprimir a stack trace
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erro interno do servidor"));
        }
    }

    @GetMapping
    public ResponseEntity<List<CotacaoDTO>> listarTodos() {
        return ResponseEntity.ok(cotacaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CotacaoDTO> buscarPorId(@PathVariable Long id) {
        Optional<CotacaoDTO> cotacao = cotacaoService.buscarPorId(id);
        return cotacao.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<CotacaoDTO> atualizarStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String status = body.get("status");
        return cotacaoService.atualizarStatus(id, status).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        cotacaoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
