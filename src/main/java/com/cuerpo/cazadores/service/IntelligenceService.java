package com.cuerpo.cazadores.service;

import com.cuerpo.cazadores.dto.TriangulationResponse;
import com.cuerpo.cazadores.entity.Pillar;
import com.cuerpo.cazadores.repository.PillarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntelligenceService {

    @Autowired
    private PillarRepository pillarRepository;

    public TriangulationResponse calcularTriangulacion() {
        List<Pillar> pillars = pillarRepository.findAll();

        if (pillars.isEmpty()) {
            return new TriangulationResponse(
                    new TriangulationResponse.Posicion(0.0, 0.0),
                    0.0,
                    "Insufficient Pillar data to calculate triangulation."
            );
        }

        // Simple algorithm: weighted average of positions
        double sumaX = 0.0;
        double sumaY = 0.0;
        int cantidad = pillars.size();

        for (Pillar pillar : pillars) {
            sumaX += pillar.getPosX();
            sumaY += pillar.getPosY();
        }

        double promedioX = sumaX / cantidad;
        double promedioY = sumaY / cantidad;

        // Calculate confidence level based on dispersion
        // The closer the pillars are, the higher the confidence
        double varianzaX = 0.0;
        double varianzaY = 0.0;

        for (Pillar pillar : pillars) {
            varianzaX += Math.pow(pillar.getPosX() - promedioX, 2);
            varianzaY += Math.pow(pillar.getPosY() - promedioY, 2);
        }

        double desviacion = Math.sqrt((varianzaX + varianzaY) / cantidad);
        // Normalize confidence (lower deviation = higher confidence)
        // Use a function that gives values between 0.5 and 0.9
        double nivelConfianza = Math.max(0.5, Math.min(0.9, 1.0 - (desviacion / 1000.0)));

        String descripcion = cantidad >= 3 
                ? "High probability of demonic presence at the given coordinates."
                : "Insufficient data. More Pillar information is required.";

        return new TriangulationResponse(
                new TriangulationResponse.Posicion(promedioX, promedioY),
                nivelConfianza,
                descripcion
        );
    }
}

