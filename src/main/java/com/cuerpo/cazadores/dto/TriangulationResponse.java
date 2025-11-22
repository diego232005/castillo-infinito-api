package com.cuerpo.cazadores.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TriangulationResponse {
    private Posicion posiblePosicionMuzan;
    private Double nivelConfianza;
    private String descripcion;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Posicion {
        private Double x;
        private Double y;
    }
}

