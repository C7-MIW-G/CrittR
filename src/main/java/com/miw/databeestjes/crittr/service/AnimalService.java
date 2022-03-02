package com.miw.databeestjes.crittr.service;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.model.CrittrUser;

import java.util.List;
import java.util.Optional;


public interface AnimalService {

    List<Animal> getAll();

    List<Animal> getAll(String keyword);

    void save (Animal animal);

    void delete (Animal animal);

    public void addFavouriteAnimal(CrittrUser user, Animal animal);

    Optional<Animal> findByAnimalId(long animalId);
}
