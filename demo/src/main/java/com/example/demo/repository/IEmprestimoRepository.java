package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entities.Emprestimo;


@Repository
public interface IEmprestimoRepository extends JpaRepository <Emprestimo,Long>{

    List<Emprestimo> findByDataDevolucaoAfter(LocalDateTime dataDevolucao);
    
}
