package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.dto.AnimalDTO;
import com.miw.databeestjes.crittr.model.*;
import com.miw.databeestjes.crittr.service.AnimalService;
import com.miw.databeestjes.crittr.service.UserAnimalFavouritesService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Ivo Didden <i.l.didden@st.hanze.nl>
 * <p>
 * This file is part of our Crittr Project
 */
@RestController
public class FavouriteRestController {

    UserAnimalFavouritesService userAnimalFavouritesService;
    AnimalService animalService;

    public FavouriteRestController(UserAnimalFavouritesService userAnimalFavouritesService, AnimalService animalService) {
        this.userAnimalFavouritesService = userAnimalFavouritesService;
        this.animalService = animalService;
    }

    @PostMapping("/api/animals/favourite")
    protected ResponseEntity<?> favouriteAnimal(@Valid @RequestBody AnimalFavouriteCriteria animalId,
                                   Errors errors,
                                   @AuthenticationPrincipal CrittrUser user) {

        AnimalFavouriteResponse response = new AnimalFavouriteResponse();

        if (errors.hasErrors()) {
            response.setMessage(errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(response);
        }

        Optional<Animal> optionalAnimal = animalService.findByAnimalId(animalId.getAnimalId());
        if (optionalAnimal.isPresent()){
            Animal animal = optionalAnimal.get();
            UserAnimalFavourites userAnimalFavourites = new UserAnimalFavourites();
            userAnimalFavourites.setAnimal(animal);
            userAnimalFavourites.setUser(user);
            userAnimalFavouritesService.save(userAnimalFavourites);
            response.setFavourited(true);
            response.setMessage("Animal favourited!");
        }

        return ResponseEntity.ok(response);
    }
}
