package com.miw.databeestjes.crittr.service.implementation;

import com.miw.databeestjes.crittr.model.UserAnimalFavourites;
import com.miw.databeestjes.crittr.repository.UserAnimalFavouritesRepository;
import com.miw.databeestjes.crittr.service.UserAnimalFavouritesService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bianca Burema <b.burema@st.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
 */

@Service
public class UserAnimalFavouritesServiceImplementation implements UserAnimalFavouritesService {
    UserAnimalFavouritesRepository userAnimalFavouritesRepository;

    public UserAnimalFavouritesServiceImplementation(UserAnimalFavouritesRepository userAnimalFavouritesRepository) {
        this.userAnimalFavouritesRepository = userAnimalFavouritesRepository;
    }

    public List<UserAnimalFavourites> getAll() {
        return userAnimalFavouritesRepository.findAll();
    }

    public void save(UserAnimalFavourites userAnimalFavourites) {
        userAnimalFavouritesRepository.save(userAnimalFavourites);
    }

}
