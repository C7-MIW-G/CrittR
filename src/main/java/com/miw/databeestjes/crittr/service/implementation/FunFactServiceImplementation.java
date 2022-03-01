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
    public FunFact getRandomFact() {
        List<FunFact> factList = funFactRepository.findAll();
        int selectedFact = (int)(Math.random() * (factList.size()));
        return factList.get(selectedFact);
    }
}
