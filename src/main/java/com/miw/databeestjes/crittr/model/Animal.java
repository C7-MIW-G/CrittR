package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

/**
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl
 * <p>
 * This class describes an animal
 */

@Entity
@Getter @Setter
public class Animal {

    @Id
    @GeneratedValue
    protected long animalId;

    @NotEmpty
    protected String species;

    @NotEmpty
    protected String name;

    protected AnimalStatus status = AnimalStatus.PRESENT;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    protected LocalDate birthDate;

    private String biography;

    @Lob
    private byte[] animalPicture;

    @OneToMany(mappedBy = "animal")
    private Set<UserAnimalFavourites> likes;

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public byte[] getDefaultPicture() {
        File file = new File("src/main/resources/static/assets/CrittrDefaultAnimalPicture.png");
        byte[] defaultPicture = new byte[(int) file.length()];
        try {
            defaultPicture = new FileInputStream(file).readAllBytes();
        } catch (IOException s) {
            s.printStackTrace();
        }
        return defaultPicture;
    }
}
