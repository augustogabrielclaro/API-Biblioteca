package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MultaDTO;
import com.example.demo.dto.MultaDTOPatch;
import com.example.demo.dto.MultaDTOPost;
import com.example.demo.service.MultaService;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "Multas", description = "Endpoints para gerenciamento de multas")
@RestController
@RequestMapping("api/multas")
public class MultaController {

    @Autowired
    private MultaService multaService;

    @Operation(summary = "Lista todas as multas", description = "Método para listar todas as multas existentes")
    @GetMapping("/todasMultas")
    public ResponseEntity<List<MultaDTO>> listarMultas() {
        List<MultaDTO> multas = multaService.listarTodos();

        return ResponseEntity.ok(multas);
    }


    @Operation(summary = "Busca uma multa por ID", description = "Retorna uma multa de acordo com o ID desejado")
    @GetMapping("/{id}")
    public ResponseEntity<MultaDTO> buscarPorId(@PathVariable Long id) {
        Optional<MultaDTO> multaDTO = multaService.buscarPorId(id);
        
        return multaDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria uma multa nova", description = "Método para criar uma multa nova no banco de dados")
    @PostMapping("/criarMulta")
    public ResponseEntity<ApiResponse<MultaDTO>> criarMulta(@Valid @RequestBody MultaDTOPost multaDTO) {
        try {
            MultaDTO savedMulta = multaService.salvar(multaDTO);

            ApiResponse<MultaDTO> response = new ApiResponse<>(savedMulta);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido", e.getMessage());
            
            ApiResponse<MultaDTO> response = new ApiResponse<>(errorResponse);
            
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());

            ApiResponse<MultaDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @Operation(summary = "Deleta uma multa", description = "Método para deletar uma multa do banco de dados")
    @DeleteMapping("/{id}/deletar")
    public ResponseEntity<Void> deletarMulta(@PathVariable Long id) {
        multaService.deletar(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza uma multa por completo", description = "Método para atualizar uma multa do banco de dados por completo")
    @PutMapping("/{id}/sobrescrever")
    public ResponseEntity<ApiResponse<MultaDTO>> atualizarMulta(@PathVariable Long id, @Valid @RequestBody MultaDTOPost multaDTO) {
        try {
            MultaDTO savedMulta = multaService.sobrescreverMulta(id, multaDTO);

            ApiResponse<MultaDTO> response = new ApiResponse<>(savedMulta);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido!", e.getMessage());

            ApiResponse<MultaDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());

            ApiResponse<MultaDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Atualiza os campos desejados da multa", description = "Método para atualizar os campos desejados de uma multa do banco de dados")
    @PatchMapping("/{id}/atualizarMulta")
    public ResponseEntity<ApiResponse<MultaDTO>> metodoPatch(@PathVariable Long id, @Valid @RequestBody MultaDTOPatch multaDTO) {
        try {
            MultaDTO savedMulta = multaService.metodoPatch(id, multaDTO);

            ApiResponse<MultaDTO> response = new ApiResponse<>(savedMulta);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido!", e.getMessage());

            ApiResponse<MultaDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());

            ApiResponse<MultaDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Atualiza o valor de uma multa", description = "Método para atualizar o valor de uma multa do banco de dados")
    @PatchMapping("/{id}/atualizarValor")
    public ResponseEntity<ApiResponse<MultaDTO>> atualizar_valor(@PathVariable Long id) {
        try {
            MultaDTO savedMulta = multaService.atualizar_valor(id);

            ApiResponse<MultaDTO> response = new ApiResponse<>(savedMulta);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido!", e.getMessage());

            ApiResponse<MultaDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());

            ApiResponse<MultaDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Atualiza o status da multa para PAGA", description = "Método para atualizar o status de uma multa para PAGA")
    @PatchMapping("/{id}/pagar")
    public ResponseEntity<ApiResponse<MultaDTO>> pagar_multa(@PathVariable Long id) {
        try {
            MultaDTO savedMulta = multaService.pagar_multa(id);

            ApiResponse<MultaDTO> response = new ApiResponse<>(savedMulta);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido!", e.getMessage());

            ApiResponse<MultaDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());

            ApiResponse<MultaDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
