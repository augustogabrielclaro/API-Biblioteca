package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.Entities.Multa;
import com.example.demo.dto.MultaDTO;

@Mapper(componentModel = "spring")
public interface IMultaMapper {

    @Mapping(target = "emprestimo_id", source = "emprestimo.id")
    MultaDTO toDTO(Multa multa);
    
    @Mapping(target = "emprestimo.id", source = "emprestimo_id")
    Multa toEntity (MultaDTO multaDTO);

    List<MultaDTO> toDTOList (List<Multa> multas);

    List<Multa> toEntityList(List<MultaDTO> multasDTO);

    
}
