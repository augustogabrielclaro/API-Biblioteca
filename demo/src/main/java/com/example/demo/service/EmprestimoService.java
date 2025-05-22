package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Cliente;
import com.example.demo.Entities.Emprestimo;
import com.example.demo.Entities.Livro;
import com.example.demo.dto.EmprestimoDTO;
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

    public EmprestimoDTO salvar(EmprestimoDTO emprestimoDTO){
        Cliente cliente = clienteRepository.findById(emprestimoDTO.getClienteId())
        .orElseThrow(()->new RuntimeException("Cliente nao encontrado"));

        Livro livro = livroRepository.findById(emprestimoDTO.getLivroId())
        .orElseThrow(()-> new RuntimeException("Livro nao enontrado"));

        
        Emprestimo emprestimo = emprestimoMapper.toEntity(emprestimoDTO);
        emprestimo.setCliente(cliente);
        emprestimo.setLivro(livro);

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

        emprestimo.setStatus(newStatus);

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
