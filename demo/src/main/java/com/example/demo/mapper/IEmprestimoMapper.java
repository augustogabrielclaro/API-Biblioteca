package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.Entities.Emprestimo;
import com.example.demo.dto.EmprestimoDTO;

@Mapper(componentModel = "spring")
public interface IEmprestimoMapper {

    @Mapping(target = "clienteId", source = "cliente.id")
    @Mapping(target = "livroId", source = "livro.id")
    EmprestimoDTO toDto(Emprestimo emprestimo);

    @Mapping(target = "cliente.id", source = "clienteId")
    @Mapping(target = "livro.id", source = "livroId")
    Emprestimo toEntity(EmprestimoDTO emprestimoDTO);

    List<EmprestimoDTO> toDTOList (List <Emprestimo> emprestimos); 

    List<Emprestimo> toEntityList (List<EmprestimoDTO> emprestimosDTO);

}



