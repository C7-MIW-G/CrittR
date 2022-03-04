package com.miw.databeestjes.crittr.repository;

import com.miw.databeestjes.crittr.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Optional<Animal> getById(long animalId);

    @Query("SELECT a FROM Animal a WHERE a.name LIKE %?1%"
            + " OR a.species LIKE %?1%")
    List<Animal> search(String keyword);

    @Query("SELECT DISTINCT a.species FROM Animal a")
    List<String> listSpecies();
}

