package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmprestimoDTO;
import com.example.demo.dto.EmprestimoDTOPost;
import com.example.demo.service.EmprestimoService;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
            @Valid @RequestBody EmprestimoDTOPost emprestimoDTO) {
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
    @PatchMapping("/Status/1{id}")
    public ResponseEntity<Void> atualizarStatus(@PathVariable Long id, @RequestBody @Valid EmprestimoDTO emprestimo) {

        emprestimoservice.atualizarStatus(id, emprestimo.getStatusCode());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Consultar Empréstimos por Cliente", description = "Metódo consulta por id cliente")
    @GetMapping("/{id}")
    public ResponseEntity<List<EmprestimoDTO>> buscarCliente(@PathVariable Long id) {
        List<EmprestimoDTO> emprestimos= emprestimoservice.buscarEmprestimo(id);
        if (emprestimos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
         return ResponseEntity.ok(emprestimos);
    }

    @Operation(summary = "Consultar Emprestimos atrasados", description = "Método de Consultar emprestimo com data de devolução atrasados")
    @GetMapping("/atrasados")
    public ResponseEntity<List<EmprestimoDTO>> emprestimosAtrasados() {

        List<EmprestimoDTO> atrasados = emprestimoservice.buscaAtrasados();
        return ResponseEntity.ok(atrasados);
    }

    @Operation(summary = "Registrar devolucao", description = "Método de registrar devolucao do livro")
    @PatchMapping("/Devolucao/{id}")
    public ResponseEntity<Void> devolucao(@PathVariable Long id) {

        emprestimoservice.registrarDevolucao(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Buscar todos Emprestimos", description = "Método de consultar todos os emprestimo")
    @GetMapping("/todosEmprestimo")
    public ResponseEntity <List<EmprestimoDTO>> listarTodos(){
        List<EmprestimoDTO> emprestimosAtivos = emprestimoservice.listarTodos();

        return ResponseEntity.ok(emprestimosAtivos);
        
    }

    @Operation(summary = "Deletar Emprestimo", description = "Método de deletar emprestimo por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmprestimo (@PathVariable Long id) {
        emprestimoservice.delete(id);

        return ResponseEntity.noContent().build();
    }

        @Operation(summary = "Atualiza um Empréstimo por completo", description = "Método para atualizar um emprestimo do banco de dados por completo")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmprestimoDTO>> atualizarEmprestimo(@PathVariable Long id,@RequestBody EmprestimoDTO emprestimoDTO) {
        try {
            EmprestimoDTO saveEmprestimo = emprestimoservice.sobrescreverEmprestimo(id, emprestimoDTO);

            ApiResponse<EmprestimoDTO> response = new ApiResponse<>(saveEmprestimo);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido!", e.getMessage());

            ApiResponse<EmprestimoDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());

            ApiResponse<EmprestimoDTO> response = new ApiResponse<>(errorResponse);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}