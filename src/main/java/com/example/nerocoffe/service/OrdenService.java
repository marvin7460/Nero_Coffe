package com.example.nerocoffe.service;

import com.example.nerocoffe.model.Orden;
import com.example.nerocoffe.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenService {
    @Autowired
    private OrdenRepository ordenRepository;

    public List<Orden> getAllOrdenes() {
        return ordenRepository.findAll();
    }

    public Optional<Orden> getOrdenById(Long id) {
        return ordenRepository.findById(id);
    }

    public Orden createOrden(Orden orden) {
        return ordenRepository.save(orden);
    }
}
