package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Bianca Burema <b.burema@st.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
 */

@Entity
@Getter @Setter
public class UserAnimalFavourites implements Serializable {

    @EmbeddedId
    UserAnimalFavouritesKey id;

    @ManyToOne
    @MapsId("animalId")
    @JoinColumn(name = "animal_id")
    Animal animal;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    CrittrUser user;
}


