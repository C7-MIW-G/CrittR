package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Bianca Burema <b.burema@st.hanze.nl>
 * <p>
 * This class creates the composite key for the animal favourites functionality.
 */

@Embeddable
@Getter @Setter
public class UserAnimalFavouritesKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "animal_id")
    Long animalId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAnimalFavouritesKey that = (UserAnimalFavouritesKey) o;
        return Objects.equals(userId, that.userId) && Objects.equals(animalId, that.animalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, animalId);
    }
}
