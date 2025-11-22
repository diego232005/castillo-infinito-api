package com.cuerpo.cazadores.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pilares")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pillar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private Double posX;

    @Column(nullable = false)
    private Double posY;

    @Column(nullable = false)
    private String estado;

    public Pillar(String nombre, Double posX, Double posY, String estado) {
        this.nombre = nombre;
        this.posX = posX;
        this.posY = posY;
        this.estado = estado;
    }
}

