package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Bianca Burema <b.burema@st.hanze.nl>
 * <p>
 * This class create the join table for animal favourites.
 */

@Entity
@Getter @Setter
public class UserAnimalFavourites {

    @EmbeddedId
    private UserAnimalFavouritesKey userAnimalFavouritesKey = new UserAnimalFavouritesKey();

    @ManyToOne
    @MapsId("animalId")
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private CrittrUser user;
}


