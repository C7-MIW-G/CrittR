package com.miw.databeestjes.crittr.service;

import com.miw.databeestjes.crittr.model.EduInfo;

import java.util.List;

/**
 * @author Ivo Didden <i.l.didden@st.hanze.nl>
 * <p>
 * This file is part of our Crittr Project
 */

public interface EduInfoService {
    List<EduInfo> getEduInfoBySpecies(String species);

}
