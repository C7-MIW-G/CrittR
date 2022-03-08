package com.miw.databeestjes.crittr.service;

import com.miw.databeestjes.crittr.model.FunFact;

import java.util.List;

public interface FunFactService {
    FunFact getRandomFact();

    void addFunFact(String fact);

    public List<FunFact> getAll();
}
