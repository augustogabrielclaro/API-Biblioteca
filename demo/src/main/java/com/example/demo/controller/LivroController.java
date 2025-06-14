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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LivroDTO;
import com.example.demo.service.LivroService;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Livros", description = "Endpoints para gerenciar os livros")
@RestController
@RequestMapping("api/livros")
public class LivroController {
    @Autowired
    private LivroService livroService;

    @Operation(summary = "Cria um novo livro", description = "Cadastra um novo livro")
    @PostMapping
    public ResponseEntity<ApiResponse<LivroDTO>> criarLivro(@Valid @RequestBody LivroDTO livroDTO) {
        try {
            LivroDTO novoLivro = livroService.salvar(livroDTO);

            ApiResponse<LivroDTO> response = new ApiResponse<>(novoLivro);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        catch(IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse("Argumento inválido", e.getMessage());

            ApiResponse<LivroDTO> response = new ApiResponse<>(error);

            return ResponseEntity.badRequest().body(response);
        }
        catch(Exception e) {
            ErrorResponse error = new ErrorResponse("Erro interno:", e.getMessage());

            ApiResponse<LivroDTO> response = new ApiResponse<>(error);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @Operation(summary = "Busca todos os livros do banco", description = "Retorna todos os livros")
    @GetMapping
    public ResponseEntity<ApiResponse<List<LivroDTO>>> buscaTodos() {
        try {
            List<LivroDTO> livrosDTO = livroService.listarTodos();

            ApiResponse<List<LivroDTO>> response = new ApiResponse<>(livrosDTO);

            return ResponseEntity.ok(response);
        }
        catch(IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse("Argumento inválido:", e.getMessage());

            ApiResponse<List<LivroDTO>> response = new ApiResponse<>(error);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    @Operation(summary = "Busca um livro por ID", description = "Retorna os detalhes de um livro esperado")
    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> buscaPorId(@PathVariable Long id) {
        Optional<LivroDTO> livroDTO = livroService.buscarPorId(id);

        return livroDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Busca todos os livros disponiveis para empréstimo", description = "Retorna os livros disponiveis")
    @GetMapping("/disponiveis")
    public ResponseEntity<ApiResponse<List<LivroDTO>>> buscaLivrosDisponiveis() {
        try {
            List<LivroDTO> livrosDisponiveis = livroService.listarLivrosDisponiveis();

            ApiResponse<List<LivroDTO>> response = new ApiResponse<>(livrosDisponiveis);

            return ResponseEntity.ok(response);
        }
        catch(IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse("Argumento invalido", e.getMessage());

            ApiResponse<List<LivroDTO>> response = new ApiResponse<>(error);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        catch(Exception e) {
            ErrorResponse error = new ErrorResponse("Argumento invalido", e.getMessage());

            ApiResponse<List<LivroDTO>> response = new ApiResponse<>(error);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @Operation(summary = "Busca todos os livros emprestados", description = "Retorna os livros emprestados")
    @GetMapping("/emprestados")
    public ResponseEntity<ApiResponse<List<LivroDTO>>> buscaLivrosEmprestados() {
        try {
            List<LivroDTO> livrosEmprestados = livroService.listarLivrosEmprestados();

            ApiResponse<List<LivroDTO>> response = new ApiResponse<>(livrosEmprestados);

            return ResponseEntity.ok(response);
        }
        catch(IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse("Argumento invalido", e.getMessage());

            ApiResponse<List<LivroDTO>> response = new ApiResponse<>(error);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        catch(Exception e) {
            ErrorResponse error = new ErrorResponse("Argumento invalido", e.getMessage());

            ApiResponse<List<LivroDTO>> response = new ApiResponse<>(error);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @Operation(summary = "Busca a quantidade de cada livro")
    @GetMapping("/quantidadeDeCadaLivro")
    public ResponseEntity<ApiResponse<List<LivroDTO>>> buscaQuantidadeDeCadaLivro() {
        try {
            List<LivroDTO> quantidadeDeCadaLivro = livroService.listarQuantidadeDeCadaLivro();

            ApiResponse<List<LivroDTO>> response = new ApiResponse<>(quantidadeDeCadaLivro);

            return ResponseEntity.ok(response);
        }
        catch(IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse("Argumento invalido", e.getMessage());

            ApiResponse<List<LivroDTO>> response = new ApiResponse<>(error);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        catch(Exception e) {
            ErrorResponse error = new ErrorResponse("Argumento invalido", e.getMessage());

            ApiResponse<List<LivroDTO>> response = new ApiResponse<>(error);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @Operation(summary = "Busca os livros pelo titulo, autor ou categoria", description = "Retorna uma lista de livros onde o titulo, autor ou categoria são comparados com a pesquisa")
    @GetMapping("/pesquisa")
    public ResponseEntity<ApiResponse<List<LivroDTO>>> buscaLivrosPelaCategoria(@RequestParam String pesquisa) {
        try {
            List<LivroDTO> listaDeLivros = livroService.listarLivroPorTituloAutorCategoria(pesquisa);

            ApiResponse<List<LivroDTO>> response = new ApiResponse<>(listaDeLivros);

            return ResponseEntity.ok(response);
        }
        catch(IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse("Argumento invalido", e.getMessage());

            ApiResponse<List<LivroDTO>> response = new ApiResponse<>(error);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        catch(Exception e) {
            ErrorResponse error = new ErrorResponse("Argumento invalido", e.getMessage());

            ApiResponse<List<LivroDTO>> response = new ApiResponse<>(error);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @Operation(summary = "Atualiza as informações do livro", description = "Faz a atualização de todas as informações do livro")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<LivroDTO>> atualizaLivro(@PathVariable Long id, @Valid @RequestBody LivroDTO livroDTO) {
        try {
            LivroDTO livroAtualizado = livroService.atualizar(id, livroDTO);

            ApiResponse<LivroDTO> response = new ApiResponse<>(livroAtualizado);

            return ResponseEntity.ok(response);
        }
        catch(IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse("Argumento invalido", e.getMessage());

            ApiResponse<LivroDTO> response = new ApiResponse<>(error);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        catch(Exception e) {
            ErrorResponse error = new ErrorResponse("Erro interno", e.getMessage());

            ApiResponse<LivroDTO> response = new ApiResponse<>(error);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @Operation(summary = "Deleta um livro do sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaPorId(@PathVariable Long id) {
        livroService.deletarPorId(id);

        return ResponseEntity.noContent().build();
    }
}
