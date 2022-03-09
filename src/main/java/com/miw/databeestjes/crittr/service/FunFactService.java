package com.miw.databeestjes.crittr.service;

import com.miw.databeestjes.crittr.model.FunFact;

import java.util.List;

public interface FunFactService {

    FunFact getRandomFactBySpecies(String species);

    void addFunFact(String fact, String species);

    List<FunFact> getAll();
}
