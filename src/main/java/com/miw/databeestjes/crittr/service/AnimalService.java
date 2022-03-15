package com.miw.databeestjes.crittr.service;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.model.AnimalStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface AnimalService {

    List<Animal> getAll();

    List<Animal> getAll(String keyword);

    void save (Animal animal);

    void delete (Animal animal);

    Optional<Animal> findByAnimalId(long animalId);

    List<String> listSpecies();

    List<Animal> listByStatus(AnimalStatus status);

    List<Animal> getBySpeciesAndStatus(String species, AnimalStatus status);

    void addNewAnimal(String species, String name, LocalDate birthdate, String biography, byte[] picture, AnimalStatus... status);
}
