package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.Entities.Reserva;
import com.example.demo.dto.ReservaDTO;

@Mapper(componentModel = "spring")
public interface IReservaMapper {
    
    @Mapping(target = "cliente_id", source = "cliente.id")
    @Mapping(target = "livro_id", source = "livro.id")
    ReservaDTO toDTO(Reserva reserva);

    @Mapping(target = "cliente.id", source = "cliente_id")
    @Mapping(target = "livro.id", source = "livro_id")
    Reserva toEntity(ReservaDTO reservaDTO);

    List<ReservaDTO> toDTOList(List<Reserva> reservas);

    List<Reserva> toEntityList(List<ReservaDTO> reservasDTO);
}
