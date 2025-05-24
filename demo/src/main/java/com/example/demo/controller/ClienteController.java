package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.service.ClienteService;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Clientes", description = "Endpoints para gerenciar os clientes")
@RestController
@RequestMapping("api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Cria um novo cliente", description = "Cadastra um novo cliente na base de dados")
    @PostMapping
    public ResponseEntity<ApiResponse<ClienteDTO>> criarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        try {
            ClienteDTO novoCliente = clienteService.salvar(clienteDTO);
            ApiResponse<ClienteDTO> response = new ApiResponse<>(novoCliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse("Argumento inválido", e.getMessage());
            ApiResponse<ClienteDTO> response = new ApiResponse<>(error);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse("Erro interno:", e.getMessage());
            ApiResponse<ClienteDTO> response = new ApiResponse<>(error);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Atualiza o Cliente", description = "Atualiza os dados de um cliente existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteDTO>> atualizarCliente(
            @PathVariable Long id,
            @Valid @RequestBody ClienteDTO clienteDTO) {
        try {
            ClienteDTO clienteAtualizado = clienteService.atualizar(id, clienteDTO);
            ApiResponse<ClienteDTO> response = new ApiResponse<>(clienteAtualizado);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse("Cliente não encontrado", e.getMessage());
            ApiResponse<ClienteDTO> response = new ApiResponse<>(error);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse("Erro interno", e.getMessage());
            ApiResponse<ClienteDTO> response = new ApiResponse<>(error);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Retorna todos os clientes", description = "Lista todos os clientes cadastrados")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> buscarTodos() {
        List<ClienteDTO> clientesDTO = clienteService.listarTodos();
        if (clientesDTO.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientesDTO);
    }

    @Operation(summary = "Busca cliente por ID", description = "Retorna o cliente com o ID fornecido")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) {
        Optional<ClienteDTO> clienteDTO = clienteService.buscarPorId(id);
        return clienteDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deleta um cliente por ID", description = "Remove o cliente com base no ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
