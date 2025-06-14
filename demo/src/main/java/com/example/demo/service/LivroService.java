package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Livro;
import com.example.demo.dto.LivroDTO;
import com.example.demo.mapper.ILivroMapper;
import com.example.demo.repository.ILivroRepository;

@Service
public class LivroService {
    @Autowired
    private ILivroRepository livroRepository;
    
    @Autowired
    private ILivroMapper livroMapper;

    public LivroDTO salvar(LivroDTO livroDTO) {
        if (livroDTO.getTitulo() == null || livroDTO.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("O campo titulo não pode estra vazio");
        }
        if (livroDTO.getAutor() == null || livroDTO.getAutor().isEmpty()) {
            throw new IllegalArgumentException("O campo Autor não pode estra vazio");
        }
        if (livroDTO.getIsbn() == null || livroDTO.getIsbn().isEmpty()) {
            throw new IllegalArgumentException("O campo ISBN não pode estar vazio");
        }
        if (livroDTO.getCategoria() == null || livroDTO.getCategoria().isEmpty()) {
            throw new IllegalArgumentException("A campo Categoria não pode estar vazia");
        }

        Livro livro = livroMapper.toEntity(livroDTO);

        return livroMapper.toDTO(livroRepository.save(livro));
    }

    public List<LivroDTO> listarTodos() {
        List<Livro> livros = livroRepository.findAll();

        if (livros.isEmpty()) {
            throw new RuntimeException("Nenhum livro foi encontrado");
        }
        
        return livroMapper.toDTOList(livros);
    }

    public Optional<LivroDTO> buscarPorId(Long id) {
        return livroRepository.findById(id).map(livroMapper::toDTO);
    }

    public List<LivroDTO> listarLivrosDisponiveis() {
        List<Livro> livrosDisponiveis = livroRepository.buscarLivrosDisponiveis();

        if (livrosDisponiveis.isEmpty()) {
            throw new RuntimeException("Nenhum livro foi encontrado");
        }

        return livroMapper.toDTOList(livrosDisponiveis);
    }

    public List<LivroDTO> listarLivrosEmprestados() {
        List<Livro> livrosEmprestados = livroRepository.buscarLivrosEmprestados();

        if (livrosEmprestados.isEmpty()) {
            throw new RuntimeException("Nenhum livro foi encontrado");
        }

        return livroMapper.toDTOList(livrosEmprestados);
    }

    public List<LivroDTO> listarQuantidadeDeCadaLivro() {
        List<Livro> quantidadeCadaLivro = livroRepository.buscarQuantidadeDeCadaLivro();

        if (quantidadeCadaLivro.isEmpty()) {
            throw new RuntimeException("Nenhum livro foi encontrado");
        }

        return livroMapper.toDTOList(quantidadeCadaLivro);
    }

    public List<LivroDTO> listarLivroPorTituloAutorCategoria(String pesquisa) {
        List<Livro> listaDeLivros = livroRepository.buscarLivrosPorTituloAutorCategoriaContendo(pesquisa);

        if (listaDeLivros == null || listaDeLivros.isEmpty()) {
            throw new RuntimeException("Nenhum livro foi encontrado");
        }

        return livroMapper.toDTOList(listaDeLivros);
    }

    public LivroDTO atualizar(Long id, LivroDTO livroDTO) {
        Livro livroExistente = livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        
        if (livroDTO.getTitulo() == null || livroDTO.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("O campo titulo não pode estra vazio");
        }
        if (livroDTO.getAutor() == null || livroDTO.getAutor().isEmpty()) {
            throw new IllegalArgumentException("O campo Autor não pode estra vazio");
        }
        if (livroDTO.getIsbn() == null || livroDTO.getIsbn().isEmpty()) {
            throw new IllegalArgumentException("O campo ISBN não pode estar vazio");
        }
        if (livroDTO.getCategoria() == null || livroDTO.getCategoria().isEmpty()) {
            throw new IllegalArgumentException("A campo Categoria não pode estar vazia");
        }

        livroExistente.setTitulo(livroDTO.getTitulo());
        livroExistente.setAutor(livroDTO.getAutor());
        livroExistente.setIsbn(livroDTO.getIsbn());
        livroExistente.setCategoria(livroDTO.getCategoria());

        return livroMapper.toDTO(livroRepository.save(livroExistente));
    }

    public void deletarPorId(Long id) {
        livroRepository.deleteById(id);
    }
}
