package com.example.demo.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.DestinoDTO;
import com.example.demo.service.Utils.DestinoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Tag(name = "Destino", description = "Endpoints para gerenciamento dos destinos")
@RestController
@RequestMapping("/api/destinos")
public class DestinoController {

    @Autowired
    private DestinoService destinoService; 

    @PostMapping
    public ResponseEntity<DestinoDTO> salvar(@RequestBody @Valid DestinoDTO destinoDTO) {
        DestinoDTO destinoSalvo = destinoService.salvar(destinoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(destinoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<DestinoDTO>> listarTodosDes() {
        List<DestinoDTO> destinos = destinoService.listarTodosDes();
        return ResponseEntity.ok(destinos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinoDTO> buscarDestinoPorId(@PathVariable Long id) {
        DestinoDTO destino = destinoService.buscarDestinoPorId(id);
        return ResponseEntity.ok(destino);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DestinoDTO> atualizarDestino(@PathVariable Long id, @RequestBody @Valid DestinoDTO destinoDTO) {
        DestinoDTO destinoAtualizado = destinoService.atualizarDestino(id, destinoDTO);
        return ResponseEntity.ok(destinoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDestino(@PathVariable Long id) {
        destinoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Tratamento de exceções
    @ControllerAdvice
    public static class GlobalExceptionHandler {
        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleGeneralException(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor: " + ex.getMessage());
        }
    }
}
