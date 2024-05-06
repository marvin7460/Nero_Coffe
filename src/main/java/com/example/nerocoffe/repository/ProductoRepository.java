package com.example.nerocoffe.repository;

import com.example.nerocoffe.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Product,Long> {
}
