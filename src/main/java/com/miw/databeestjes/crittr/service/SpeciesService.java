package com.miw.databeestjes.crittr.service;

import com.miw.databeestjes.crittr.model.Species;

import java.util.List;

public interface SpeciesService {
     List<Species> getAll();

     void save (Species species);
}
