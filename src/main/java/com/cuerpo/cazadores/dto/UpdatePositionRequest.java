package com.cuerpo.cazadores.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePositionRequest {

    @NotNull(message = "Pillar ID is required")
    private Long pilarId;

    @NotNull(message = "X position is required")
    private Double posX;

    @NotNull(message = "Y position is required")
    private Double posY;

    @NotNull(message = "Status is required")
    private String estado;
}

