package com.cuerpo.cazadores.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReconstructMessageRequest {

    @NotNull(message = "Reconstructed content is required")
    private String contenidoReconstruido;
}

