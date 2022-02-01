package com.miw.databeestjes.crittr.repository;

import com.miw.databeestjes.crittr.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesRepository extends JpaRepository<Species, String> {
}
