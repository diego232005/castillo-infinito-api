package com.cuerpo.cazadores.repository;

import com.cuerpo.cazadores.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByPilarId(Long pilarId);
}

