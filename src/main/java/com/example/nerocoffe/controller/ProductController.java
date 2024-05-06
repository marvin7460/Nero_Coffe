package com.example.nerocoffe.controller;

import com.example.nerocoffe.model.Product;
import com.example.nerocoffe.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductController {
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProductos() {
        List<Product> productos = productoRepository.findAll();
        return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<Product> createProducto(@RequestBody Product producto) {
        Product newProducto = productoRepository.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProducto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductoById(@PathVariable Long id) {
        Optional<Product> producto = productoRepository.findById(id);

        if (producto.isPresent()) {
            return ResponseEntity.ok(producto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id) {
        Optional<Product> producto = productoRepository.findById(id);

        if (producto.isPresent()) {
            productoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
