package com.example.demo.Controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entities.Destino;
import com.example.demo.dto.DestinoDTO;
import com.example.demo.mapper.DestinoMapper;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.DestinoService;
import com.example.demo.service.Utils.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Destino", description = "Endpoints para gerenciamento dos destinos")
@RestController
@RequestMapping("/api/destinos")
public class DestinoController {

    @Autowired
    private DestinoService destinoService;

    @Autowired
    private DestinoMapper destinoMapper; 

    @PostMapping
    public ResponseEntity<DestinoDTO> criar(@Valid @RequestBody DestinoDTO destinoDTO) {
        DestinoDTO novoDestino = destinoService.criarDestino(destinoDTO);
        DestinoDTO resposta = destinoMapper.toDto(novoDestino);  
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping
    public ResponseEntity<List<DestinoDTO>> listarTodos() {
        return ResponseEntity.ok(destinoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinoDTO> buscarPorId(@PathVariable Long id) {
        return destinoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DestinoDTO> atualizar(@PathVariable Long id, @RequestBody DestinoDTO dto) {
        return destinoService.atualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        destinoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
