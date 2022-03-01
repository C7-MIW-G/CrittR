package com.miw.databeestjes.crittr.dto;

import com.miw.databeestjes.crittr.model.Animal;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import java.time.LocalDate;

/**
 * @author Ivo Didden <i.l.didden@st.hanze.nl>
 * <p>
 * This file is part of our Crittr Project
 */

@Getter @Setter
public class AnimalDTO {

    private long animalId;

    private String species;

    private String name;

    private String status;

    private LocalDate birthDate;

    private int age;

    @Lob
    private byte[] picture;

    public AnimalDTO(Animal animal) {
        this.animalId = animal.getAnimalId();
        this.species = animal.getSpecies();
        this.name = animal.getName();
        this.status = animal.getStatus().getDisplayName();
        this.birthDate = animal.getBirthDate();
        this.age = animal.getAge();
        this.picture = animal.getAnimalPicture();
    }
}
