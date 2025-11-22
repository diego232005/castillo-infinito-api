package com.cuerpo.cazadores.repository;

import com.cuerpo.cazadores.entity.Pillar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PillarRepository extends JpaRepository<Pillar, Long> {
    Optional<Pillar> findByNombre(String nombre);
}

