package com.miw.databeestjes.crittr.service;

import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.model.UserAnimalFavourites;

import java.util.List;
import java.util.Optional;

public interface UserAnimalFavouritesService {

    List<UserAnimalFavourites> getAll();

    List<UserAnimalFavourites> getByUser(CrittrUser user);

    void save(UserAnimalFavourites UserAnimalFavourites);
}
