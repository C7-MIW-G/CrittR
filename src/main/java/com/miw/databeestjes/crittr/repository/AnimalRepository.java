package com.miw.databeestjes.crittr.repository;

import com.miw.databeestjes.crittr.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
