package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Cliente;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.mapper.IClienteMapper;
import com.example.demo.repository.IClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IClienteMapper iclienteMapper;

        public List<ClienteDTO> listarTodos() {
        return iclienteMapper.toDTOList(clienteRepository.findAll());
    }

    public ClienteDTO salvar(ClienteDTO clienteDTO) {
        Cliente cliente = iclienteMapper.toEntity(clienteDTO);

        return iclienteMapper.toDTO(clienteRepository.save(cliente));
    }

    public Optional<ClienteDTO> buscarPorId(Long id) {
        return clienteRepository.findById(id).map(iclienteMapper::toDTO);
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}
