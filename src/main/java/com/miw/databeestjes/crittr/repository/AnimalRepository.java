package com.miw.databeestjes.crittr.repository;

import com.miw.databeestjes.crittr.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Optional<Animal> getById(long animalId);
}

