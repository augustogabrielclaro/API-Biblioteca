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
        Livro livro = livroMapper.toEntity(livroDTO);

        return livroMapper.toDTO(livroRepository.save(livro));
    }

    public List<LivroDTO> listarTodos() {
        List<Livro> livros = livroRepository.findAll();

        if (livros.isEmpty()) {
            new RuntimeException("Nenhum livro foi encontrado");
        }
        
        return livroMapper.toDTOList(livros);
    }

    public Optional<LivroDTO> buscarPorId(Long id) {
        return livroRepository.findById(id).map(livroMapper::toDTO);
    }

    public List<LivroDTO> listarLivrosDisponiveis() {
        List<Livro> livros = livroRepository.buscarLivrosDisponiveis();

        if (livros.isEmpty()) {
            new RuntimeException("Nenhum livro foi encontrado");
        }

        return livroMapper.toDTOList(livros);
    }

    public LivroDTO atualizar(Long id, LivroDTO livroDTO) {
        Livro livroExistente = livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        
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
