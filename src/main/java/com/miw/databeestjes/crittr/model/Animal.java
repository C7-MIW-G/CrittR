package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;


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

    @ManyToOne
    protected Species species;

    protected String name;

    @OneToMany(mappedBy = "animal")
    protected List<Report> reports;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected LocalDate birthDate;

    public int getAge () {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
