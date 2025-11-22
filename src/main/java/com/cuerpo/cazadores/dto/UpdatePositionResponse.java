package com.cuerpo.cazadores.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePositionResponse {
    private String mensaje;
    private PillarResponse pilar;
}

