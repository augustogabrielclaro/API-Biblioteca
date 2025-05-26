package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Cliente;
import com.example.demo.Entities.Livro;
import com.example.demo.Entities.Reserva;
import com.example.demo.dto.MultaDTO;
import com.example.demo.dto.ReservaDTO;
import com.example.demo.mapper.IClienteMapper;
import com.example.demo.mapper.IReservaMapper;
import com.example.demo.repository.IClienteRepository;
import com.example.demo.repository.ILivroRepository;
import com.example.demo.repository.IReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private IReservaRepository reservaRepository;

    @Autowired
    private IReservaMapper reservaMapper;

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private ILivroRepository livroRepository;

    public List<ReservaDTO> listarTodos() {
        return reservaMapper.toDTOList(reservaRepository.findAll());
    }

    public Optional<ReservaDTO> buscarPorId(Long id) {
        return reservaRepository.findById(id).map(reservaMapper::toDTO);
    }

    public ReservaDTO salvar(ReservaDTO reservaDTO) {
        Cliente cliente = clienteRepository.findById(reservaDTO.getCliente_id())
        .orElseThrow(()-> new IllegalArgumentException("Cliente não encontrado!"));

        Livro livro = livroRepository.findById(reservaDTO.getLivro_id())
        .orElseThrow(()-> new IllegalArgumentException("Livro não encontrado!"));

        Reserva reserva = new Reserva();

        reserva.setCliente(cliente);
        reserva.setLivro(livro);
        reserva.setDataReserva(reservaDTO.getDataReserva());
        reserva.setStatus(reservaDTO.getStatus());

        return reservaMapper.toDTO(reservaRepository.save(reserva));
    }

    public void deletar(Long id) {
        reservaRepository.deleteById(id);

    }
}
