package com.example.demo.mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


import com.example.demo.Entities.Emprestimo;
import com.example.demo.dto.EmprestimoDTO;
import com.example.demo.enums.StatusEmprestimo;

@Mapper(componentModel = "spring")
public interface IEmprestimoMapper {

    @Mapping(target = "clienteId", source = "cliente.id")
    @Mapping(target = "livroId", source = "livro.id")
    @Mapping(target = "statusCode", source = "status", qualifiedByName = "statusToCode")
    EmprestimoDTO toDto(Emprestimo emprestimo);


    @Mapping(target = "cliente.id", source = "clienteId")
    @Mapping(target = "livro.id", source = "livroId")
    @Mapping(target = "status", source = "statusCode", qualifiedByName = "codetoStatus")
    Emprestimo toEntity(EmprestimoDTO emprestimoDTO);   

    List<EmprestimoDTO> toDTOList(List<Emprestimo> emprestimos);

    List<Emprestimo> toEntityList(List<EmprestimoDTO> emprestimosDTO);

    @Named("statusToCode")
    default Integer statusToCode(StatusEmprestimo status){
        return (status != null) ? status.getCodigo() : null;
    }

    @Named("codetoStatus")
          default StatusEmprestimo codetoStatus(Integer codigo){
            return (codigo != null) ? StatusEmprestimo.fromCodigo(codigo) : null;
    }
}
