package com.miw.databeestjes.crittr.repository;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.model.UserAnimalFavourites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserAnimalFavouritesRepository extends JpaRepository<UserAnimalFavourites, Long> {

    @Query("SELECT u FROM UserAnimalFavourites u WHERE u.user = ?1")
    List<UserAnimalFavourites> getByUser(CrittrUser user);

    @Query("SELECT u FROM UserAnimalFavourites u WHERE u.user = ?1 AND u.animal = ?2")
    UserAnimalFavourites getByUserAndAnimal(CrittrUser user, Animal animal);

}
