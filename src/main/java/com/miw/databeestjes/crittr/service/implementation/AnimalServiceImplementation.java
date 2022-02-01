package com.miw.databeestjes.crittr.service.implementation;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.repository.AnimalRepository;
import com.miw.databeestjes.crittr.service.AnimalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl
 * <p>
 * This class regulates access between the repository and the controller
 */

@Service
public class AnimalServiceImplementation implements AnimalService{

    AnimalRepository animalRepository;

    public AnimalServiceImplementation(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public List<Animal> getAll() {
        return animalRepository.findAll();
    }

    @Override
    public void save(Animal animal) {
        animalRepository.save(animal);
    }

    @Override
    public Optional<Animal> findByAnimalId(long animalId){
        return (animalRepository.getById(animalId));
    }
}
