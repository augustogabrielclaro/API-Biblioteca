package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Cliente;
import com.example.demo.Entities.Livro;
import com.example.demo.Entities.Reserva;
import com.example.demo.dto.ReservaDTO;
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

    // GET
    public List<ReservaDTO> listarTodos() {
        return reservaMapper.toDTOList(reservaRepository.findAll());
    }

    // GET
    public Optional<ReservaDTO> buscarPorId(Long id) {
        return reservaRepository.findById(id).map(reservaMapper::toDTO);
    }

    // POST
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

    // DELETE
    public void deletar(Long id) {
        if (!reservaRepository.existsById(id)) {
            throw new IllegalArgumentException("Reserva com o ID " + id + " não encontrado!");
        }
        
        reservaRepository.deleteById(id);
    }

    // PUT
    public ReservaDTO sobrescreverReserva(Long id, ReservaDTO reservaDTO) {
        Reserva reserva = reservaRepository.findById(id).
                                orElseThrow(() -> new IllegalArgumentException("Reserva não encontrada!"));
        
        Cliente cliente = clienteRepository.findById(reservaDTO.getCliente_id())
        .orElseThrow(()-> new IllegalArgumentException("Cliente não encontrado!"));

        Livro livro = livroRepository.findById(reservaDTO.getLivro_id())
        .orElseThrow(()-> new IllegalArgumentException("Livro não encontrado!"));
        
        reserva.setCliente(cliente);
        reserva.setLivro(livro);
        reserva.setDataReserva(reservaDTO.getDataReserva());
        reserva.setStatus(reservaDTO.getStatus());

        return reservaMapper.toDTO(reservaRepository.save(reserva));
        
    }
}
