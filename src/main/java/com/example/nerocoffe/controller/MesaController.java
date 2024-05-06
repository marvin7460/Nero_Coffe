package com.example.nerocoffe.controller;

import com.example.nerocoffe.model.Mesa;
import com.example.nerocoffe.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mesas")
public class MesaController {
    @Autowired
    private MesaRepository mesaRepository;

    @GetMapping
    public ResponseEntity<List<Mesa>> getAllMesas() {
        List<Mesa> mesas = mesaRepository.findAll();
        return ResponseEntity.ok(mesas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> getMesaById(@PathVariable Long id) {
        Optional<Mesa> mesa = mesaRepository.findById(id);
        return mesa.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Mesa> createMesa(@RequestBody Mesa mesa) {
        Mesa newMesa = mesaRepository.save(mesa);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMesa);
    }
}
