package com.miw.databeestjes.crittr.model;

import com.miw.databeestjes.crittr.dto.CrittrUserDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl
 * <p>
 * Вот, что программа делает
 */

@Getter @Setter
public class CrittrUserSearchResponse {

    String msg;
    List<CrittrUserDTO> dtos;


}
