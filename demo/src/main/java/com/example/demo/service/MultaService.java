package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Multa;
import com.example.demo.dto.MultaDTO;
import com.example.demo.mapper.MultaMapper;
import com.example.demo.repository.MultaRepository;

@Service
public class MultaService {
    
    @Autowired
    private MultaRepository multaRepository;

    @Autowired
    private MultaMapper multaMapper;

    public List<MultaDTO> listarTodos() {
        return multaMapper.toDTOList(multaRepository.findAll());
    }

    public MultaDTO salvar(MultaDTO multaDTO) {
        Multa multa = multaMapper.toEntity(multaDTO);

        return multaMapper.toDTO(multaRepository.save(multa));
    }

    public Optional<MultaDTO> buscarPorId(Long id) {
        return multaRepository.findById(id).map(multaMapper::toDTO);
    }

    public void deletar(Long id) {
        multaRepository.deleteById(id);
    }
}
