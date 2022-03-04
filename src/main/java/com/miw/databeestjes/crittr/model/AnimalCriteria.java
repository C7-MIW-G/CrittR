package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ivo Didden <i.l.didden@st.hanze.nl>
 * <p>
 * This file is part of our Crittr Project
 */
@Getter @Setter
public class AnimalCriteria {
    String keyword;

    AnimalStatus status;
}
