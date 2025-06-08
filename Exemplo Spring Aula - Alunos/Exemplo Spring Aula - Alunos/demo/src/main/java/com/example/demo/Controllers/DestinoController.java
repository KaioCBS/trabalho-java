package com.example.demo.Controllers;

import com.example.demo.dto.DestinoDTO;
import com.example.demo.service.Utils.DestinoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Destino", description = "Endpoints para gerenciamento dos destinos")
@RestController
@RequestMapping("/api/destinos")
public class DestinoController {

    @Autowired
    private DestinoService destinoService; 

    @PostMapping
    public ResponseEntity<DestinoDTO> salvar(@RequestBody @Valid DestinoDTO destinoDTO) {
        DestinoDTO destinoSalvo = destinoService.salvar(destinoDTO);
        return ResponseEntity.status(201).body(destinoSalvo);
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
}