package com.example.nerocoffe.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Mesa {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Date getHoraCreacion() {
        return horaCreacion;
    }

    public void setHoraCreacion(Date horaCreacion) {
        this.horaCreacion = horaCreacion;
    }

    public Date getHoraUltimaActualizacion() {
        return horaUltimaActualizacion;
    }

    public void setHoraUltimaActualizacion(Date horaUltimaActualizacion) {
        this.horaUltimaActualizacion = horaUltimaActualizacion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    @OneToOne
    @JoinColumn(name = "orden_id")
    private Orden orden;
    private Date horaCreacion;
    private Date horaUltimaActualizacion;

    // Constructor, getters y setters
}
