package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entities.Livro;

@Repository
public interface ILivroRepository extends JpaRepository <Livro, Long>{
    @Query(
        value = "SELECT L.id, L.titulo, L.autor, L.isbn, L.quantidade, L.categoria " + 
        "FROM livros L LEFT JOIN emprestimos E ON L.id = E.livro_id " + 
        "WHERE STATUS = 'Concluido' OR E.id is null",
        nativeQuery = true
    )
    List<Livro> buscarLivrosDisponiveis();
}