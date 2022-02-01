package com.miw.databeestjes.crittr.service.implementation;

import com.miw.databeestjes.crittr.model.Species;
import com.miw.databeestjes.crittr.repository.SpeciesRepository;
import com.miw.databeestjes.crittr.service.SpeciesService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl
 * <p>
 * This class regulates database access with regard to species
 */

@Service
public class SpeciesServiceImplementation implements SpeciesService {

    private SpeciesRepository speciesRepository;

    public SpeciesServiceImplementation(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    @Override
    public List<Species> getAll() {
        return speciesRepository.findAll();
    }

    @Override
    public void save(Species species) {
        speciesRepository.save(species);
    }
}
