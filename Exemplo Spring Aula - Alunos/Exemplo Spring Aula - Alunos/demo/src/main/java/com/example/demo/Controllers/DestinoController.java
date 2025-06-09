package com.example.demo.Controllers;

import com.example.demo.dto.DestinoDTO;
import com.example.demo.service.Utils.DestinoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
<<<<<<< HEAD
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
>>>>>>> 944e750ea295c808f1837f2ee5d96a6be2d53685

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
    public ResponseEntity<List<DestinoDTO>> listarTodos() {
        List<DestinoDTO> destinos = destinoService.listarTodos();
        return ResponseEntity.ok(destinos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinoDTO> buscarPorId(@PathVariable Long id) {
        DestinoDTO destino = destinoService.buscarPorId(id);
        return ResponseEntity.ok(destino);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DestinoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid DestinoDTO destinoDTO) {
        DestinoDTO destinoAtualizado = destinoService.atualizar(id, destinoDTO);
        return ResponseEntity.ok(destinoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        destinoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
<<<<<<< HEAD

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
=======
}
>>>>>>> 944e750ea295c808f1837f2ee5d96a6be2d53685
