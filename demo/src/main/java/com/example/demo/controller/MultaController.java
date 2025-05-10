package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MultaDTO;
import com.example.demo.service.MultaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Tag(name = "multas", description = "Endpoints para gerenciamento de multas")
@RestController
@RequestMapping("/api/multas")
public class MultaController {

    @Autowired
    private MultaService multaService;

    @Operation(summary = "Busca uma multa por ID", description = "Retorna uma multa de acordo com o ID desejado")
    @GetMapping("/{id}")
    public ResponseEntity<MultaDTO> buscarPorId(@PathVariable Long id) {
        Optional<MultaDTO> multaDTO = multaService.buscarPorId(id);
        
        return multaDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}
