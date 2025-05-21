package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Emprestimo;
import com.example.demo.Entities.Multa;
import com.example.demo.dto.MultaDTO;
import com.example.demo.enums.StatusMulta;
import com.example.demo.mapper.IMultaMapper;
import com.example.demo.repository.IEmprestimoRepository;
import com.example.demo.repository.IMultaRepository;

@Service
public class MultaService {
    
    @Autowired
    private IMultaRepository multaRepository;

    @Autowired
    private IMultaMapper multaMapper;

    @Autowired
    private IEmprestimoRepository emprestimoRepository;

    public List<MultaDTO> listarTodos() {
        return multaMapper.toDTOList(multaRepository.findAll());
    }

    public MultaDTO salvar(MultaDTO multaDTO) {
        Emprestimo emprestimo = emprestimoRepository.findById(multaDTO.getEmprestimo_id()).
                                orElseThrow(() -> new RuntimeException("Empréstimo não encontrado!"));

        if (multaDTO.getValor().compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("O valor não pode ser negativo");
        
        Multa multa = multaMapper.toEntity(multaDTO);
        multa.setEmprestimo(emprestimo);

        return multaMapper.toDTO(multaRepository.save(multa));
    }

    public Optional<MultaDTO> buscarPorId(Long id) {
        return multaRepository.findById(id).map(multaMapper::toDTO);
    }

    public void deletar(Long id) {
        if (!multaRepository.existsById(id)) {
            throw new RuntimeException("Multa com o ID " + id + " não encontrado!");
        }
        
        multaRepository.deleteById(id);
    }

    public MultaDTO sobrescreverMulta(Long id, MultaDTO multaDTO) {
        Multa multa = multaRepository.findById(id).
                                orElseThrow(() -> new RuntimeException("Multa não encontrada!"));
        
        Emprestimo emprestimo = emprestimoRepository.findById(multaDTO.getEmprestimo_id()).
                                orElseThrow(() -> new RuntimeException("Empréstimo não encontrado!"));
        
        if (multaDTO.getValor().compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("O valor não pode ser negativo");
        
        multa.setEmprestimo(emprestimo);
        multa.setDataPagamento(multaDTO.getDataPagamento());
        multa.setStatus(StatusMulta.fromCodigo(multaDTO.getStatusCode()));
        multa.setValor(multaDTO.getValor());
        
        return multaMapper.toDTO(multaRepository.save(multa));
    }
}
