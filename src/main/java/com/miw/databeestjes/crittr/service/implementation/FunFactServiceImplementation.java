package com.miw.databeestjes.crittr.service.implementation;

import com.miw.databeestjes.crittr.model.FunFact;
import com.miw.databeestjes.crittr.repository.FunFactRepository;
import com.miw.databeestjes.crittr.service.FunFactService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ivo Didden <i.l.didden@st.hanze.nl>
 * <p>
 * This file is part of our Crittr Project
 */

@Service
public class FunFactServiceImplementation implements FunFactService {

    FunFactRepository funFactRepository;

    public FunFactServiceImplementation(FunFactRepository funFactRepository) {
        this.funFactRepository = funFactRepository;
    }

    @Override
    public FunFact getRandomFactBySpecies(String species) {
        List<FunFact> factListOfSpecies = funFactRepository.findBySpecies(species);
        if(factListOfSpecies.isEmpty()){
            FunFact fact = new FunFact();
            fact.setFact("No facts known yet about these animals");
            return fact;
        }
        int selectedFact = (int)(Math.random() * (factListOfSpecies.size()));
        return factListOfSpecies.get(selectedFact);
    }

    public void addFunFact(String fact, String species) {
        FunFact funFact = new FunFact();
        funFact.setFact(fact);
        funFact.setSpecies(species);
        funFactRepository.save(funFact);
    }

    public List<FunFact> getAll() {
        return funFactRepository.findAll();
    }
}
