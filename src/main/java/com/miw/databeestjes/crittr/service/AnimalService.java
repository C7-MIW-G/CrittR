package com.miw.databeestjes.crittr.service;

import com.miw.databeestjes.crittr.model.Animal;

import java.util.List;


public interface AnimalService {

    List<Animal> getAll();

    void save (Animal animal);
}
