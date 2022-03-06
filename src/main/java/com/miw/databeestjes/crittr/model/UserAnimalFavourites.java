package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLInsert;

import javax.persistence.*;

/**
 * @author Bianca Burema <b.burema@st.hanze.nl>
 * <p>
 * This class creates the join table for animal favourites.
 */

@Entity
@Getter @Setter
@SQLInsert(sql = "INSERT IGNORE INTO user_animal_favourites (animal_id, user_id) VALUES (? , ? )")
public class UserAnimalFavourites {

    @EmbeddedId
    private UserAnimalFavouritesKey userAnimalFavouritesKey = new UserAnimalFavouritesKey();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private CrittrUser user;

    @ManyToOne
    @MapsId("animalId")
    @JoinColumn(name = "animal_id")
    private Animal animal;
}


