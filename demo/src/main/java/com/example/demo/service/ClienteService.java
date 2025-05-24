package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Cliente;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.mapper.IClienteMapper;
import com.example.demo.repository.ClienteRepository;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

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

    public ClienteDTO atualizar(Long id, ClienteDTO clienteDTO) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if (clienteOptional.isEmpty()) {
            throw new IllegalArgumentException("Cliente com ID " + id + " não encontrado.");
        }

        Cliente cliente = clienteOptional.get();

        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setEndereco(clienteDTO.getEndereco());

        Cliente clienteAtualizado = clienteRepository.save(cliente);
        return new ClienteDTO(clienteAtualizado.getId(), clienteAtualizado.getNome(), clienteAtualizado.getEmail(),
                   clienteAtualizado.getTelefone(), clienteAtualizado.getEndereco());
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}
