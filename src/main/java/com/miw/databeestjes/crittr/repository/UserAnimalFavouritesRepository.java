package com.miw.databeestjes.crittr.repository;

import com.miw.databeestjes.crittr.model.UserAnimalFavourites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserAnimalFavouritesRepository extends JpaRepository<UserAnimalFavourites, Long> {

    @Query("SELECT u FROM UserAnimalFavourites u WHERE u.user.userId = ?1")
    Optional<UserAnimalFavourites> findByUserId(Long userId);

}
