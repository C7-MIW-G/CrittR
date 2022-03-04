package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.model.UserAnimalFavourites;
import com.miw.databeestjes.crittr.model.UserAnimalFavouritesKey;
import com.miw.databeestjes.crittr.service.AnimalService;
import com.miw.databeestjes.crittr.service.UserAnimalFavouritesService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Bianca Burema <b.burema@st.hanze.nl>
 * <p>
 * This controller handles all functionality nessecary to favourite an animal.
 */

@Controller
public class UserAnimalFavouritesController {

    private UserAnimalFavouritesService userAnimalFavouritesService;
    private AnimalService animalService;

    public UserAnimalFavouritesController(UserAnimalFavouritesService userAnimalFavouritesService, AnimalService animalService) {
        this.userAnimalFavouritesService = userAnimalFavouritesService;
        this.animalService = animalService;
    }

    @PostMapping("/animals/details/{animalId}")
    public String addFavouriteAnimal(@ModelAttribute("animalId") Animal animal,
                                     @AuthenticationPrincipal CrittrUser user, BindingResult result) {

        if (result.hasErrors()) {
            return "redirect:/animals";
        }
        UserAnimalFavourites userAnimalFavourites = new UserAnimalFavourites();
        UserAnimalFavouritesKey userAnimalFavouritesKey = new UserAnimalFavouritesKey();
        userAnimalFavouritesKey.setAnimalId(animal.getAnimalId());
        userAnimalFavouritesKey.setUserId(user.getUserId());
        userAnimalFavourites.setUserAnimalFavouritesKey(userAnimalFavouritesKey);
        userAnimalFavourites.setAnimal(animal);
        userAnimalFavourites.setUser(user);
        userAnimalFavouritesService.save(userAnimalFavourites);
        return "redirect:/animals";
    }

}
