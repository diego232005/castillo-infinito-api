package com.cuerpo.cazadores.controller;

import com.cuerpo.cazadores.dto.UpdatePositionRequest;
import com.cuerpo.cazadores.dto.UpdatePositionResponse;
import com.cuerpo.cazadores.dto.PillarResponse;
import com.cuerpo.cazadores.service.PillarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pilares")
public class PillarController {

    @Autowired
    private PillarService pillarService;

    @GetMapping("/{id}")
    public ResponseEntity<PillarResponse> obtenerPilarPorId(@PathVariable Long id) {
        PillarResponse pillar = pillarService.obtenerPilarPorId(id);
        return ResponseEntity.ok(pillar);
    }

    @PostMapping("/actualizar-posicion")
    public ResponseEntity<UpdatePositionResponse> actualizarPosicion(
            @Valid @RequestBody UpdatePositionRequest request) {
        UpdatePositionResponse response = pillarService.actualizarPosicion(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

