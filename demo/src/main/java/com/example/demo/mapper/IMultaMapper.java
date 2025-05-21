package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.example.demo.Entities.Multa;
import com.example.demo.dto.MultaDTO;
import com.example.demo.enums.StatusMulta;

@Mapper(componentModel = "spring")
public interface IMultaMapper {

    @Mapping(target = "emprestimo_id", source = "emprestimo.id")
    @Mapping(target = "statusCode", source = "status", qualifiedByName = "statusToCode")
    MultaDTO toDTO(Multa multa);
    
    @Mapping(target = "emprestimo.id", source = "emprestimo_id")
    @Mapping(target = "status", source = "statusCode", qualifiedByName = "codeToStatus")
    Multa toEntity (MultaDTO multaDTO);

    List<MultaDTO> toDTOList (List<Multa> multas);

    List<Multa> toEntityList(List<MultaDTO> multasDTO);


    @Named("statusToCode")
    default Integer statusToCode(StatusMulta status) {
        return (status != null) ? status.getCodigo() : null;
    }

    @Named("codeToStatus")
    default StatusMulta codeToStatus(Integer codigo) {
        return (codigo != null) ? StatusMulta.fromCodigo(codigo) : null;
    }

    
}
