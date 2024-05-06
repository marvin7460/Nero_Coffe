package com.example.nerocoffe.service;

import com.example.nerocoffe.model.Mesa;
import com.example.nerocoffe.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MesaService {
    @Autowired
    private MesaRepository mesaRepository;

    public List<Mesa> getAllMesas() {
        return mesaRepository.findAll();
    }

    public Optional<Mesa> getMesaById(Long id) {
        return mesaRepository.findById(id);
    }

    public Mesa createMesa(Mesa mesa) {
        return mesaRepository.save(mesa);
    }
}
