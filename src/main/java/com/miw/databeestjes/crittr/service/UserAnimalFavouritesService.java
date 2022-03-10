package com.miw.databeestjes.crittr.service;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.model.UserAnimalFavourites;

import java.util.List;
import java.util.Optional;

public interface UserAnimalFavouritesService {

    List<UserAnimalFavourites> getAll();

    List<UserAnimalFavourites> getByUser(CrittrUser user);

    void save(UserAnimalFavourites UserAnimalFavourites);
    void delete(UserAnimalFavourites userAnimalFavourites);

    UserAnimalFavourites getByUserAndAnimal(CrittrUser user, Animal animal);
}
