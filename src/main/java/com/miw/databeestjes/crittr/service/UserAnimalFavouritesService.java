package com.miw.databeestjes.crittr.service;

import com.miw.databeestjes.crittr.model.UserAnimalFavourites;

import java.util.List;
import java.util.Optional;

public interface UserAnimalFavouritesService {

    List<UserAnimalFavourites> getAll();

    void save(UserAnimalFavourites UserAnimalFavourites);

    Optional<UserAnimalFavourites> getByUserId(long userId);
}
