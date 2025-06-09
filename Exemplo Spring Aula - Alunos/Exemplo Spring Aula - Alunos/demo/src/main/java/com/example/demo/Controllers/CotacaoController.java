package com.example.demo.Controllers;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CotacaoDTO;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.CotacaoService;
import com.example.demo.service.Utils.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Tag(name = "Cotação", description = "Endpoints para gerenciamento de cotações")
@RestController
@RequestMapping("/api/cotacoes")
public class CotacaoController {

    @Autowired
    private CotacaoService cotacaoService;

   @PostMapping
    public ResponseEntity<CotacaoDTO> criarCotacao(@RequestBody @Valid CotacaoDTO cotacaoDTO) {
        CotacaoDTO novaCotacao = cotacaoService.criarCotacao(cotacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCotacao);
    }

    @GetMapping
    public ResponseEntity<List<CotacaoDTO>> listarTodos() {
        return ResponseEntity.ok(cotacaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CotacaoDTO> buscarCotacaoPorId(@PathVariable Long id) {
        CotacaoDTO cotacao = cotacaoService.buscarCotacaoPorId(id);
        return ResponseEntity.ok(cotacao);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<CotacaoDTO> atualizarStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String status = body.get("status");
        return cotacaoService.atualizarStatus(id, status)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

     @ControllerAdvice
    public static class GlobalExceptionHandler {
        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        cotacaoService.remover(id);
        return ResponseEntity.noContent().build();
    }

     @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleGeneralException(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor: " + ex.getMessage());
        }
    }
}

