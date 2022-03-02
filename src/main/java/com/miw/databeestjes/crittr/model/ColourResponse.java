package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl
 * <p>
 * This class formulates a response with regard to the colourtheme
 */

@Getter @Setter
public class ColourResponse {
    private String msg;

    private boolean isCaretaker;
}

