package com.example.demo.Controllers;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.service.Utils.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Tag(name = "Cliente", description = "Endpoints para gerenciamento de clientes")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTO> criarCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        ClienteDTO clienteSalvo = clienteService.salvar(clienteDTO);
        return ResponseEntity.status(201).body(clienteSalvo);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        List<ClienteDTO> clientes = clienteService.listarTodos();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) {
        ClienteDTO cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ClienteDTO clienteDTO) {
        ClienteDTO clienteAtualizado = clienteService.atualizar(id, clienteDTO);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDestino(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
