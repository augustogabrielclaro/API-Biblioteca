package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Emprestimo;
import com.example.demo.dto.EmprestimoDTO;
import com.example.demo.mapper.IEmprestimoMapper;
import com.example.demo.repository.IEmprestimoRepository;


@Service
public class EmprestimoService {

@Autowired
private IEmprestimoRepository emprestimoRepository;

@Autowired
private IEmprestimoMapper emprestimoMapper;

    public EmprestimoDTO salvar(EmprestimoDTO emprestimoDTO){
        Emprestimo emprestimo = emprestimoMapper.toEntity(emprestimoDTO);

        return emprestimoMapper.toDto(emprestimoRepository.save(emprestimo));
    }

    public List<EmprestimoDTO> listarTodos(){
       return emprestimoMapper.toDTOList(emprestimoRepository.findAll());
    }

    public Optional<EmprestimoDTO> buscarPorId (Long id) {
        return emprestimoRepository.findById(id).map(emprestimoMapper::toDto);
    }
  
    public void AtualizarStatus(Long id, String newStatus){
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow(()->
        new RuntimeException("emprestimo nao encontrado"));
        
        emprestimoRepository.save(emprestimo);
    }

    public List<EmprestimoDTO> buscaAtrasados (){
        return emprestimoMapper.toDTOList(emprestimoRepository.findByDataDevolucaoAfter(LocalDateTime.now()));
    }
    
    public void deletar(Long Id){
        emprestimoRepository.deleteById(Id);
    }

    public void registrarDevolucao(Long id){
        Emprestimo emprestimo = emprestimoRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("emprestimo nao encontado"));

        if(emprestimo.getDataDevolucao() != null){
            throw new RuntimeException("o livro ja foi encontrado");
        }

        emprestimo.setDataDevolucao(LocalDateTime.now());
        emprestimoRepository.save(emprestimo);
    }

}
