package com.example.demo.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entities.Emprestimo;
import com.example.demo.enums.StatusEmprestimo;


@Repository
public interface IEmprestimoRepository extends JpaRepository <Emprestimo,Long>{

    List<Emprestimo> findByStatus(StatusEmprestimo statusEmprestimo);

    List<Emprestimo> findByClienteId(Long id);
    
}
