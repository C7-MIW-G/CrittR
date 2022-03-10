package com.miw.databeestjes.crittr.service.implementation;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.model.UserAnimalFavourites;
import com.miw.databeestjes.crittr.repository.UserAnimalFavouritesRepository;
import com.miw.databeestjes.crittr.service.UserAnimalFavouritesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<UserAnimalFavourites> getByUser(CrittrUser user) {
        return userAnimalFavouritesRepository.getByUser(user);
    }

    @Override
    public UserAnimalFavourites getByUserAndAnimal(CrittrUser user, Animal animal) {
        return userAnimalFavouritesRepository.getByUserAndAnimal(user, animal);
    }

    public void save(UserAnimalFavourites userAnimalFavourites) {
        userAnimalFavouritesRepository.save(userAnimalFavourites);
    }

    @Override
    public void delete(UserAnimalFavourites userAnimalFavourites) {
        userAnimalFavouritesRepository.delete(userAnimalFavourites);
    }


}
