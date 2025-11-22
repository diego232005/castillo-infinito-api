package com.cuerpo.cazadores.controller;

import com.cuerpo.cazadores.dto.MessageRequest;
import com.cuerpo.cazadores.dto.MessageResponse;
import com.cuerpo.cazadores.dto.ReconstructMessageRequest;
import com.cuerpo.cazadores.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mensajes")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<MessageResponse> crearMensaje(@Valid @RequestBody MessageRequest request) {
        MessageResponse response = messageService.crearMensaje(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}/reconstruir")
    public ResponseEntity<MessageResponse> reconstruirMensaje(
            @PathVariable Long id,
            @Valid @RequestBody ReconstructMessageRequest request) {
        MessageResponse response = messageService.reconstruirMensaje(id, request);
        return ResponseEntity.ok(response);
    }
}

