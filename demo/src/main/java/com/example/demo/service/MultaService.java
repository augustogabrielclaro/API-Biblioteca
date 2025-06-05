package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Emprestimo;
import com.example.demo.Entities.Multa;
import com.example.demo.dto.MultaDTO;
import com.example.demo.dto.MultaDTOPatch;
import com.example.demo.dto.MultaDTOPost;
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

    private static final BigDecimal TAXA_DIARIA = new BigDecimal(2.0);

    // GET
    public List<MultaDTO> listarTodos() {
        return multaMapper.toDTOList(multaRepository.findAll());
    }

    // POST
    public MultaDTO salvar(MultaDTOPost multaDTO) {
        Emprestimo emprestimo = emprestimoRepository.findById(multaDTO.getEmprestimo_id()).
                                orElseThrow(() -> new IllegalArgumentException("Empréstimo não encontrado!"));

        // Cálculo do valor total da multa
        Long dias_atraso = calcular_dias_atraso(emprestimo);
        BigDecimal valor_multa = calcular_valor(TAXA_DIARIA, dias_atraso);

        Multa multa = new Multa();
        multa.setDataPagamento(multaDTO.getDataPagamento());
        multa.setEmprestimo(emprestimo);
        multa.setValor(valor_multa);
        multa.setStatus(StatusMulta.fromCodigo(multaDTO.getStatusCode()));

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
    public MultaDTO sobrescreverMulta(Long id, MultaDTOPost multaDTO) {
        Multa multa = multaRepository.findById(id).
                                orElseThrow(() -> new IllegalArgumentException("Multa não encontrada!"));
        
        Emprestimo emprestimo = emprestimoRepository.findById(multaDTO.getEmprestimo_id()).
                                orElseThrow(() -> new IllegalArgumentException("Empréstimo não encontrado!"));
        
        Long dias_atraso = calcular_dias_atraso(emprestimo);
        BigDecimal valor_multa = calcular_valor(TAXA_DIARIA, dias_atraso);

        multa.setEmprestimo(emprestimo);
        multa.setDataPagamento(multaDTO.getDataPagamento());
        multa.setStatus(StatusMulta.fromCodigo(multaDTO.getStatusCode()));
        multa.setValor(valor_multa);
        
        return multaMapper.toDTO(multaRepository.save(multa));
    }

    // PATCH
    public MultaDTO metodoPatch(Long id, MultaDTOPatch multaDTO) {
        Multa multa = multaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Multa não encontrada!"));

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

    // Atualizar valor da multa
    public MultaDTO atualizar_valor (Long id) {
        Multa multa = multaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Multa não encontrada!"));

        if (multa.getStatus() == StatusMulta.PAGA) {
            throw new RuntimeException("Não é possível alterar o valor de uma multa já paga!");
        }

        Emprestimo emprestimo = multa.getEmprestimo();
        if (emprestimo == null) {
            throw new IllegalArgumentException("Multa não está associada a um empréstimo válido");
        }

        Long dias_atraso = calcular_dias_atraso(emprestimo);
        BigDecimal valor_multa = calcular_valor(TAXA_DIARIA, dias_atraso);

        multa.setValor(valor_multa);

        return multaMapper.toDTO(multaRepository.save(multa));
    }

    // Atualizar o status da multa para paga
    public MultaDTO pagar_multa(Long id) {
        if (!multaRepository.existsById(id)) {
            throw new IllegalArgumentException("Multa com o ID " + id + " não encontrado!");
        }
        Multa multa = multaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Multa não encontrada!"));

        if (multa.getStatus() == StatusMulta.PAGA) {
            throw new RuntimeException("Esta multa já está paga!");
        }

        multa.setStatus(StatusMulta.PAGA);

        return multaMapper.toDTO(multaRepository.save(multa));
    }

    // Calcular valor da multa
    public BigDecimal calcular_valor(BigDecimal valor_dia, Long dias_atraso) {
        if (dias_atraso == null || dias_atraso <= 0) {
            return BigDecimal.ZERO;
        }
        return valor_dia.multiply(BigDecimal.valueOf(dias_atraso));
    }

    // Calcular dias de atraso de um empréstimo
    public Long calcular_dias_atraso (Emprestimo emprestimo) {
        LocalDateTime data_comparativa = LocalDateTime.now();

        LocalDateTime data_emprestimo = emprestimo.getDataEmprestimo();

        LocalDateTime data_limite = data_emprestimo.plusDays(30);

        if (data_comparativa.isAfter(data_limite)) {
            return ChronoUnit.DAYS.between(data_limite, data_comparativa);
        }

        return 0L;
    }
}
