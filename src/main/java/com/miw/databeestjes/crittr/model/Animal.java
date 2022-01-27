package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.Period;

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
    private long animalId;

    private String species;

    private String name;

    private LocalDate birthDate;

    public int getAge () {
        return Period.between(LocalDate.now(), birthDate).getYears();
    }
}
