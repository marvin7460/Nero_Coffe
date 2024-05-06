package com.example.nerocoffe.controller;

import com.example.nerocoffe.model.Orden;
import com.example.nerocoffe.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ordenes")
public class OrdenController {
    @Autowired

    private OrdenRepository ordenRepository;

    @GetMapping
    public ResponseEntity<List<Orden>> getAllOrdenes() {
        List<Orden> ordenes = ordenRepository.findAll();
        return ResponseEntity.ok(ordenes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> getOrdenById(@PathVariable Long id) {
        Optional<Orden> orden = ordenRepository.findById(id);
        return orden.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Orden> createOrden(@RequestBody Orden orden) {
        Orden newOrden = ordenRepository.save(orden);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrden);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrdenById(@PathVariable Long id) {
        Optional<Orden> ordenOptional = ordenRepository.findById(id);
        if (ordenOptional.isPresent()) {
            ordenRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orden> updateOrden(@PathVariable Long id, @RequestBody Orden nuevaOrden) {
        Optional<Orden> ordenOptional = ordenRepository.findById(id);
        if (ordenOptional.isPresent()) {
            Orden orden = ordenOptional.get();
            // Actualizar los campos de la orden existente con los de la nueva orden
            orden.setProducto(nuevaOrden.getProducto());
            orden.setEstado(nuevaOrden.getEstado());
            orden.setHoraPedido(nuevaOrden.getHoraPedido());
            orden.setHoraCompletado(nuevaOrden.getHoraCompletado());
            // Continuar actualizando otros campos según sea necesario
            Orden updatedOrden = ordenRepository.save(orden);
            return ResponseEntity.ok(updatedOrden);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
