package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Bianca Burema <b.burema@st.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
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


