package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.Entities.Cliente;
import com.example.demo.Entities.Emprestimo;
import com.example.demo.Entities.Livro;
import com.example.demo.dto.EmprestimoDTO;
import com.example.demo.dto.EmprestimoDTOPost;
import com.example.demo.enums.StatusEmprestimo;
import com.example.demo.mapper.IEmprestimoMapper;
import com.example.demo.repository.IClienteRepository;
import com.example.demo.repository.IEmprestimoRepository;
import com.example.demo.repository.ILivroRepository;

@Service
public class EmprestimoService {

    @Autowired
    private IEmprestimoRepository emprestimoRepository;

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private ILivroRepository livroRepository;

    @Autowired
    private IEmprestimoMapper emprestimoMapper;

    public EmprestimoDTO salvar(EmprestimoDTOPost emprestimoDTO) {
        Cliente cliente = clienteRepository.findById(emprestimoDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente nao encontrado"));

        Livro livro = livroRepository.findById(emprestimoDTO.getLivroId())
                .orElseThrow(() -> new RuntimeException("Livro nao enontrado"));

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setStatus(StatusEmprestimo.EM_ANDAMENTO);
        emprestimo.setDataEmprestimo(LocalDateTime.now());
        LocalDateTime dataDevolucao = emprestimo.getDataEmprestimo().plusDays(30);
        emprestimo.setDataDevolucao(dataDevolucao);
        emprestimo.setCliente(cliente);
        emprestimo.setLivro(livro);
        return emprestimoMapper.toDto(emprestimoRepository.save(emprestimo));
    }

    public List<EmprestimoDTO> listarTodos() {
        return emprestimoMapper.toDTOList(emprestimoRepository.findAll());
    }

    public List<EmprestimoDTO> buscarEmprestimo(Long clienteId) {
        List<Emprestimo> emprestimos = emprestimoRepository.findByClienteId(clienteId);
        return emprestimoMapper.toDTOList(emprestimos);
    }

    public void atualizarStatus(Long id, Integer newStatus) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emprestimo nao encontrado"));

        emprestimo.setStatus(StatusEmprestimo.fromCodigo(newStatus));
        emprestimoRepository.save(emprestimo);

    }

    public List<EmprestimoDTO> buscaAtrasados() {
        List<Emprestimo> atrasados = emprestimoRepository.findByStatus(StatusEmprestimo.ATRASADO);
        return emprestimoMapper.toDTOList(atrasados);
    }

    public void delete(Long Id) {
        emprestimoRepository.deleteById(Id);
    }

    public void registrarDevolucao(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("emprestimo nao encontado"));

        if (emprestimo.getStatus() == StatusEmprestimo.CONCLUIDO ) {
            throw new RuntimeException("o livro ja foi devolvido");
        }

        emprestimo.setDataDevolucao(LocalDateTime.now());
        emprestimo.setStatus(StatusEmprestimo.CONCLUIDO);
        emprestimoRepository.save(emprestimo);
    }

    public EmprestimoDTO sobrescreverEmprestimo(Long id, EmprestimoDTO emprestimoDTO) {
        Emprestimo emprestimo = emprestimoRepository.findById(id).
                                orElseThrow(() -> new IllegalArgumentException("Emprestimo não encontrada!"));
        
        Livro buscaLivro = livroRepository.findById(emprestimoDTO.getLivroId()).
                                orElseThrow(() -> new IllegalArgumentException("Livro não encontrado!"));
            
        Cliente buscaCliente = clienteRepository.findById(emprestimoDTO.getClienteId()).
                                orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado!"));
        
        emprestimo.setCliente(buscaCliente);
        emprestimo.setLivro(buscaLivro);
        emprestimo.setDataEmprestimo(emprestimoDTO.getDataEmprestimo());
        emprestimo.setDataDevolucao(emprestimoDTO.getDataDevolucao());
        emprestimo.setStatus(StatusEmprestimo.fromCodigo(emprestimoDTO.getStatusCode()));
        
        return emprestimoMapper.toDto(emprestimoRepository.save(emprestimo));
    }
  
    

}
