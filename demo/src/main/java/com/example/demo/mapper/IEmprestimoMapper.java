package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.Entities.Emprestimo;
import com.example.demo.dto.EmprestimoDTO;

@Mapper(componentModel = "spring")
public interface IEmprestimoMapper {

    EmprestimoDTO toDto (Emprestimo emprestimo);

    Emprestimo toEntity (EmprestimoDTO emprestimoDTO);

    List<EmprestimoDTO> toDTOList (List <Emprestimo> emprestimos); 

    List<Emprestimo> toEntityList (List<EmprestimoDTO> emprestimosDTO);

}



