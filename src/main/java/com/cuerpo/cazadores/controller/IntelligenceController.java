package com.cuerpo.cazadores.controller;

import com.cuerpo.cazadores.dto.TriangulationResponse;
import com.cuerpo.cazadores.service.IntelligenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inteligencia")
public class IntelligenceController {

    @Autowired
    private IntelligenceService intelligenceService;

    @GetMapping("/triangulacion")
    public ResponseEntity<TriangulationResponse> obtenerTriangulacion() {
        TriangulationResponse response = intelligenceService.calcularTriangulacion();
        return ResponseEntity.ok(response);
    }
}

