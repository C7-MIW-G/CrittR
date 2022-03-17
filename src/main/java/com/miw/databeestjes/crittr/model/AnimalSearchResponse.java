package com.miw.databeestjes.crittr.model;

import com.miw.databeestjes.crittr.dto.AnimalDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Ivo Didden <i.l.didden@st.hanze.nl>
 * <p>
 * This class is used to formulate a response to an animal Search request
 */
@Getter @Setter
public class AnimalSearchResponse {
    String msg;
    List<AnimalDTO> dtos;
}
