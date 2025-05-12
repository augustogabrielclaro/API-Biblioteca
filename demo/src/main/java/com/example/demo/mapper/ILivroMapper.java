package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.Entities.Livro;
import com.example.demo.dto.LivroDTO;

@Mapper(componentModel = "spring")
public interface ILivroMapper {
    LivroDTO toDTO(Livro livro);

    Livro toEntity(LivroDTO livroDTO);

    List<LivroDTO> toDTOList(List<Livro> livros);

    List<Livro> toEntityList(List<LivroDTO> livrosDTO);
}
