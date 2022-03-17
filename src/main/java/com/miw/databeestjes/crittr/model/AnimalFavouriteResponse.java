package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ivo Didden <i.l.didden@st.hanze.nl>
 * <p>
 * This class is used to formulate a response to a favourite request on animal
 */

@Getter @Setter
public class AnimalFavouriteResponse {

    String message = "Failed to favourite animal";
    boolean favourited = false;
    boolean loggedIn = true;
}
