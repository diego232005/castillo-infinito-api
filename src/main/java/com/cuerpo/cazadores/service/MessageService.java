package com.cuerpo.cazadores.service;

import com.cuerpo.cazadores.dto.MessageRequest;
import com.cuerpo.cazadores.dto.MessageResponse;
import com.cuerpo.cazadores.dto.ReconstructMessageRequest;
import com.cuerpo.cazadores.entity.Message;
import com.cuerpo.cazadores.exception.ResourceNotFoundException;
import com.cuerpo.cazadores.repository.MessageRepository;
import com.cuerpo.cazadores.repository.PillarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private PillarRepository pillarRepository;

    public MessageResponse crearMensaje(MessageRequest request) {
        // Validate that the pillar exists
        pillarRepository.findById(request.getPilarId())
                .orElseThrow(() -> new ResourceNotFoundException("Pillar with ID " + request.getPilarId() + " not found"));

        Message message = new Message();
        message.setPilarId(request.getPilarId());
        message.setContenidoFragmentado(request.getContenidoFragmentado());
        message.setContenidoReconstruido(null);

        Message messageGuardado = messageRepository.save(message);

        return convertirAMensajeResponse(messageGuardado);
    }

    public MessageResponse reconstruirMensaje(Long id, ReconstructMessageRequest request) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message with ID " + id + " not found"));

        message.setContenidoReconstruido(request.getContenidoReconstruido());
        Message messageActualizado = messageRepository.save(message);

        return convertirAMensajeResponse(messageActualizado);
    }

    private MessageResponse convertirAMensajeResponse(Message message) {
        return new MessageResponse(
                message.getId(),
                message.getPilarId(),
                message.getContenidoFragmentado(),
                message.getContenidoReconstruido(),
                message.getTimestamp()
        );
    }
}

