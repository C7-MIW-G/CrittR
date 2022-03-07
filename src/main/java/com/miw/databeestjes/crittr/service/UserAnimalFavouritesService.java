package com.miw.databeestjes.crittr.service;

import com.miw.databeestjes.crittr.model.UserAnimalFavourites;

import java.util.List;

public interface UserAnimalFavouritesService {

    List<UserAnimalFavourites> getAll();

    void save(UserAnimalFavourites UserAnimalFavourites);
}
