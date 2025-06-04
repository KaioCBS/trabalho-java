package com.example.demo.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.DescontoDTO;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.DescontoService;
import com.example.demo.service.Utils.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/descontos")
public class DescontoController {

    @Autowired
    private DescontoService descontoService;

    @PostMapping
    public ResponseEntity<DescontoDTO> registrar(@RequestBody DescontoDTO dto) {
        DescontoDTO novo = descontoService.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @GetMapping
    public ResponseEntity<List<DescontoDTO>> listarTodos() {
        return ResponseEntity.ok(descontoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DescontoDTO> buscarPorId(@PathVariable Long id) {
        return descontoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cotacao/{cotacaoId}")
    public ResponseEntity<List<DescontoDTO>> listarPorCotacao(@PathVariable Long cotacaoId) {
        return ResponseEntity.ok(descontoService.listarPorCotacao(cotacaoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DescontoDTO> atualizar(@PathVariable Long id, @RequestBody DescontoDTO dto) {
        return descontoService.atualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        descontoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
