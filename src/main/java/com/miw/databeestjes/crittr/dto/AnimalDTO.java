package com.miw.databeestjes.crittr.dto;

import com.miw.databeestjes.crittr.model.Animal;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;

/**
 * @author Ivo Didden <i.l.didden@st.hanze.nl>
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl>
 * <p>
 * This class is a version of an Animal entity for display in the front-end
 */

@Getter @Setter
public class AnimalDTO {

    private long animalId;

    private String species;

    private String name;

    private String status;

    private int age;

    @Lob
    private byte[] picture;

    private boolean favourited;

    public AnimalDTO(Animal animal) {
        this.animalId = animal.getAnimalId();
        this.species = animal.getSpecies();
        this.name = animal.getName();
        this.status = animal.getStatus().getDisplayName();
        this.age = animal.getAge();
        this.picture = animal.getAnimalPicture();
    }
}
