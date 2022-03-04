package com.miw.databeestjes.crittr.service;

import com.miw.databeestjes.crittr.model.Animal;

import java.util.List;
import java.util.Optional;


public interface AnimalService {

    List<Animal> getAll();

    List<Animal> getAll(String keyword);

    void save (Animal animal);

    void delete (Animal animal);

    Optional<Animal> findByAnimalId(long animalId);

    List<String> listSpecies();
}
