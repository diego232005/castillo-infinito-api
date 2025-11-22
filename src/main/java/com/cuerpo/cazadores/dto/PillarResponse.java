package com.cuerpo.cazadores.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PillarResponse {
    private Long id;
    private String nombre;
    private Double posX;
    private Double posY;
    private String estado;
}

