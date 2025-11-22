package com.cuerpo.cazadores.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {

    @NotNull(message = "Pillar ID is required")
    private Long pilarId;

    @NotNull(message = "Fragmented content is required")
    private String contenidoFragmentado;
}

