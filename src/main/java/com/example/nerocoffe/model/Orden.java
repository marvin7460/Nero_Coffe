package com.example.nerocoffe.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Orden {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProducto() {
        return producto;
    }

    public void setProducto(Product producto) {
        this.producto = producto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getHoraPedido() {
        return horaPedido;
    }

    public void setHoraPedido(Date horaPedido) {
        this.horaPedido = horaPedido;
    }

    public Date getHoraCompletado() {
        return horaCompletado;
    }

    public void setHoraCompletado(Date horaCompletado) {
        this.horaCompletado = horaCompletado;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Product producto;
    private String estado;
    private Date horaPedido;
    private Date horaCompletado;
}