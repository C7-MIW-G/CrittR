package com.miw.databeestjes.crittr.repository;

import com.miw.databeestjes.crittr.model.FunFact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FunFactRepository extends JpaRepository<FunFact, String> {

    @Query("SELECT ff FROM FunFact ff WHERE ff.species=?1")
    List<FunFact> findBySpecies(String species);

}
