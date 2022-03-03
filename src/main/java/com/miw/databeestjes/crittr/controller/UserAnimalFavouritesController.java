package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.model.UserAnimalFavourites;
import com.miw.databeestjes.crittr.service.AnimalService;
import com.miw.databeestjes.crittr.service.UserAnimalFavouritesService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Bianca Burema <b.burema@st.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
 */

@Controller
public class UserAnimalFavouritesController {

    private UserAnimalFavouritesService userAnimalFavouritesService;
    private AnimalService animalService;

    public UserAnimalFavouritesController(UserAnimalFavouritesService userAnimalFavouritesService, AnimalService animalService) {
        this.userAnimalFavouritesService = userAnimalFavouritesService;
        this.animalService = animalService;
    }

    @PostMapping("/animals/favourite{animalId}")
    public String addFavouriteAnimal(@PathVariable("animalId") long animalId,
                                     @AuthenticationPrincipal CrittrUser user, Model model) {
        Optional<Animal> animal = animalService.findByAnimalId(animalId);
        if (animal.isEmpty()){
            return "redirect:/animals";
        }
        model.addAttribute(animal);

        Animal certainAnimal = animal.get();
        UserAnimalFavourites userAnimalFavourites = new UserAnimalFavourites();
        userAnimalFavourites.setAnimal(certainAnimal);
        userAnimalFavourites.setUser(user);
        userAnimalFavouritesService.save(userAnimalFavourites);
        return "redirect:/animals";
    }
}
