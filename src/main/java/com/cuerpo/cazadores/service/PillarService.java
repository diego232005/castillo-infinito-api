package com.cuerpo.cazadores.service;

import com.cuerpo.cazadores.dto.*;
import com.cuerpo.cazadores.entity.Pillar;
import com.cuerpo.cazadores.exception.ResourceNotFoundException;
import com.cuerpo.cazadores.repository.PillarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PillarService {

    @Autowired
    private PillarRepository pillarRepository;

    public PillarResponse obtenerPilarPorId(Long id) {
        Pillar pillar = pillarRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pillar with ID " + id + " not found"));
        
        return convertirAPilarResponse(pillar);
    }

    public UpdatePositionResponse actualizarPosicion(UpdatePositionRequest request) {
        Pillar pillar = pillarRepository.findById(request.getPilarId())
                .orElseThrow(() -> new ResourceNotFoundException("Pillar with ID " + request.getPilarId() + " not found"));

        pillar.setPosX(request.getPosX());
        pillar.setPosY(request.getPosY());
        pillar.setEstado(request.getEstado());

        Pillar pillarActualizado = pillarRepository.save(pillar);

        UpdatePositionResponse response = new UpdatePositionResponse();
        response.setMensaje("Position updated successfully.");
        response.setPilar(convertirAPilarResponse(pillarActualizado));

        return response;
    }

    public List<Pillar> obtenerTodosLosPilares() {
        return pillarRepository.findAll();
    }

    private PillarResponse convertirAPilarResponse(Pillar pillar) {
        return new PillarResponse(
                pillar.getId(),
                pillar.getNombre(),
                pillar.getPosX(),
                pillar.getPosY(),
                pillar.getEstado()
        );
    }
}

