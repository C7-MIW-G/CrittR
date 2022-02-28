package com.miw.databeestjes.crittr.model;

import com.miw.databeestjes.crittr.dto.AnimalDTO;
import com.miw.databeestjes.crittr.dto.CrittrUserDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Ivo Didden <i.l.didden@st.hanze.nl>
 * <p>
 * This file is part of our Crittr Project
 */
@Getter @Setter
public class AnimalSearchResponse {
    String msg;
    List<AnimalDTO> dtos;

}
