package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmprestimoDTO;

import com.example.demo.service.EmprestimoService;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Emprestimo", description = "Endpoints para gerenciar Emprestimo")
@RestController
@RequestMapping("api/Emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoservice;

    @Operation(summary = "Registrar Emprestimo", description = "Método  Registra um novo empréstimo de livro para um cliente")
    @PostMapping()
    public ResponseEntity<ApiResponse<EmprestimoDTO>> registrarEmprestimo(
            @Valid @RequestBody EmprestimoDTO emprestimoDTO) {
        try {

            EmprestimoDTO emprestimoSalvo = emprestimoservice.salvar(emprestimoDTO);

            ApiResponse<EmprestimoDTO> response = new ApiResponse<>(emprestimoSalvo);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido", e.getMessage());
            ApiResponse<EmprestimoDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @Operation(summary = "Atualizar Status de emprestimo", description = "Método de alterar Status de um emprestimo")
    @PatchMapping("/Status")
    public ResponseEntity<Void> AtualizarStatus(@PathVariable Long id, @RequestBody @Valid EmprestimoDTO emprestimo) {

        emprestimoservice.AtualizarStatus(id, emprestimo.getStatusCode());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Consultar Empréstimos por Cliente", description = "Metódo consulta por id cliente")
    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> buscarporID(@PathVariable Long Id) {

        Optional<EmprestimoDTO> emprestimosDTO = emprestimoservice.buscarPorId(Id);
        return emprestimosDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Consultar Emprestimos atrasados", description = "Método de Consultar emprestimo com data de devolução atrasados")
    @GetMapping("/dataDevolucao")
    public ResponseEntity<List<EmprestimoDTO>> emprestimoAtrasados() {

        List<EmprestimoDTO> atrasados = emprestimoservice.buscaAtrasados();
        return ResponseEntity.ok(atrasados);
    }

    @Operation(summary = "Registrar devolucao", description = "Método de registrar devolucao do livro")
    @PatchMapping("/Devolucao")
    public ResponseEntity<Void> Devolucao(@PathVariable Long id) {

        emprestimoservice.registrarDevolucao(id);
        return ResponseEntity.noContent().build();
    }

}