package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    protected long animalId;

    protected String species;

    protected String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected LocalDate birthDate;

    public int getAge () {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}