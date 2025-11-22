package com.cuerpo.cazadores.config;

import com.cuerpo.cazadores.entity.Pillar;
import com.cuerpo.cazadores.repository.PillarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PillarRepository pilarRepository;

    @Override
    public void run(String... args) throws Exception {
        // Initialize the three pillars with their initial positions
        if (pilarRepository.count() == 0) {
            pilarRepository.save(new Pillar("Giyu Tomioka", -500.0, -200.0, "Fighting"));
            pilarRepository.save(new Pillar("Sanemi Shinazugawa", 100.0, -100.0, "Fighting"));
            pilarRepository.save(new Pillar("Mitsuri Kanroji", 500.0, 100.0, "Fighting"));
            System.out.println("✅ Pillars initialized successfully");
        }
    }
}

