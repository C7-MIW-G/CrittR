package com.miw.databeestjes.crittr.service.implementation;

import com.miw.databeestjes.crittr.model.EduInfo;
import com.miw.databeestjes.crittr.repository.EduInfoRepository;
import com.miw.databeestjes.crittr.service.EduInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ivo Didden <i.l.didden@st.hanze.nl>
 * <p>
 * This file is part of our Crittr Project
 */

@Service
public class EduInfoServiceImplementation implements EduInfoService {

    EduInfoRepository eduInfoRepository;

    public EduInfoServiceImplementation(EduInfoRepository eduInfoRepository) {
        this.eduInfoRepository = eduInfoRepository;
    }

    @Override
    public List<EduInfo> getEduInfoBySpecies(String species) {
        return eduInfoRepository.findEduInfoBySpecies(species);
    }
}
