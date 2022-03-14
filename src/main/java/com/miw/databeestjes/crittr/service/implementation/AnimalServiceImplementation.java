package com.miw.databeestjes.crittr.service.implementation;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.model.AnimalStatus;
import com.miw.databeestjes.crittr.repository.AnimalRepository;
import com.miw.databeestjes.crittr.service.AnimalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    public List<Animal> getAll(String keyword) {
        if (keyword != null) {
            return animalRepository.search(keyword);
        }
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

    @Override
    public List<String> listSpecies() {
       return animalRepository.listSpecies();
    }

    @Override
    public List<Animal> listByStatus(AnimalStatus status) {
        return animalRepository.listByStatus(status);
    }

    @Override
    public List<Animal> getBySpeciesAndStatus(String species, AnimalStatus status) {
        return animalRepository.findBySpeciesAndStatus(species, status);
    }

    @Override
    public void delete(Animal animal) {
        animalRepository.delete(animal);
    }

    public void addNewAnimal(String species, String name, LocalDate birthdate, String biography, byte[] picture) {
        Animal animal = new Animal();
        animal.setSpecies(species);
        animal.setName(name);
        animal.setBirthDate(birthdate);
        animal.setBiography(biography);
        animal.setAnimalPicture(picture);

        animalRepository.save(animal);
    }
}
