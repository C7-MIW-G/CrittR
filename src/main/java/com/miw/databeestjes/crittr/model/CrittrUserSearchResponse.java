package com.miw.databeestjes.crittr.model;

import com.miw.databeestjes.crittr.dto.CrittrUserDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl
 * <p>
 * This class formulates a response to a search request for a user
 */

@Getter @Setter
public class CrittrUserSearchResponse {
    String msg;
    List<CrittrUserDTO> dtos;

}
