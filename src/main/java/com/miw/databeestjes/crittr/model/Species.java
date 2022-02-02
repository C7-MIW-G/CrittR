package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl
 * <p>
 * Вот, что программа делает
 */

@Entity
@Getter @Setter
public class Species {

    @Id
    private String species;

    @OneToMany(mappedBy = "species")
    private List<Animal> animals;

    @OneToMany(mappedBy = "species")
    private List<Report> reports;

    @Override
    public String toString() {
        return species;
    }
}
