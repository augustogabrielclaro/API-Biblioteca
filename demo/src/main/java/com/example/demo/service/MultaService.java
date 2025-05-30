package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Emprestimo;
import com.example.demo.Entities.Multa;
import com.example.demo.dto.MultaDTO;
import com.example.demo.dto.MultaDTOPatch;
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

    // GET
    public List<MultaDTO> listarTodos() {
        return multaMapper.toDTOList(multaRepository.findAll());
    }

    // POST
    public MultaDTO salvar(MultaDTO multaDTO) {
        Emprestimo emprestimo = emprestimoRepository.findById(multaDTO.getEmprestimo_id()).
                                orElseThrow(() -> new IllegalArgumentException("Empréstimo não encontrado!"));

        if (multaDTO.getValor().compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("O valor não pode ser negativo");
        
        Multa multa = multaMapper.toEntity(multaDTO);
        multa.setEmprestimo(emprestimo);

        return multaMapper.toDTO(multaRepository.save(multa));
    }

    // GET
    public Optional<MultaDTO> buscarPorId(Long id) {
        return multaRepository.findById(id).map(multaMapper::toDTO);
    }

    // DELETE
    public void deletar(Long id) {
        if (!multaRepository.existsById(id)) {
            throw new IllegalArgumentException("Multa com o ID " + id + " não encontrado!");
        }
        
        multaRepository.deleteById(id);
    }

    // PUT
    public MultaDTO sobrescreverMulta(Long id, MultaDTO multaDTO) {
        Multa multa = multaRepository.findById(id).
                                orElseThrow(() -> new IllegalArgumentException("Multa não encontrada!"));
        
        Emprestimo emprestimo = emprestimoRepository.findById(multaDTO.getEmprestimo_id()).
                                orElseThrow(() -> new IllegalArgumentException("Empréstimo não encontrado!"));
        
        if (multaDTO.getValor().compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("O valor não pode ser negativo");
        
        multa.setEmprestimo(emprestimo);
        multa.setDataPagamento(multaDTO.getDataPagamento());
        multa.setStatus(StatusMulta.fromCodigo(multaDTO.getStatusCode()));
        multa.setValor(multaDTO.getValor());
        
        return multaMapper.toDTO(multaRepository.save(multa));
    }

    // PATCH
    public MultaDTO metodoPatch(Long id, MultaDTOPatch multaDTO) {
        Multa multa = multaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Multa não encontrada!"));

        if (multaDTO.getValor() != null) {
            if (multaDTO.getValor().compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("O valor não pode ser negativo");
            multa.setValor(multaDTO.getValor());
        }
        if (multaDTO.getStatusCode() != null) {
            multa.setStatus(StatusMulta.fromCodigo(multaDTO.getStatusCode()));
        }
        if (multaDTO.getEmprestimo_id() != null) {
            Emprestimo emprestimo = emprestimoRepository.findById(multaDTO.getEmprestimo_id()).orElseThrow(() -> new IllegalArgumentException("Empréstimo não encontrado!"));
            multa.setEmprestimo(emprestimo);
        }
        if (multaDTO.getDataPagamento() != null) {
            multa.setDataPagamento(multaDTO.getDataPagamento());
        }

        return multaMapper.toDTO(multaRepository.save(multa));
    }


}
