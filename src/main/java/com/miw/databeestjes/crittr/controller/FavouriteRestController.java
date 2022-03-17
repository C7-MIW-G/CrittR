package com.miw.databeestjes.crittr.controller;

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
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl
 * <p>
 * This class formulates a response to requests to favourite an animal
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

        if(user == null) {
            return setLoggedInStatus(response);
        }

        Optional<Animal> optionalAnimal = animalService.findByAnimalId(animalId.getAnimalId());
        if (optionalAnimal.isPresent()) {
            List<UserAnimalFavourites> userFavourites = userAnimalFavouritesService.getByUser(user);
            List<Animal> userFavouriteAnimals = getAnimalsFromAnimalFavourites(userFavourites);
            Animal animal = optionalAnimal.get();
            toggleFavourite(user, response, userFavouriteAnimals, animal);
        }
        return ResponseEntity.ok(response);
    }

    private void toggleFavourite(CrittrUser user, AnimalFavouriteResponse response, List<Animal> userFavouriteAnimals, Animal animal) {
        if (userFavouriteAnimals.contains(animal)) {
            removeFromFavourites(user, animal);
            response.setFavourited(false);
            response.setMessage("Animal Unfavourited!");
        }
        else {
            addToFavourites(user, animal);
            response.setFavourited(true);
            response.setMessage("Animal favourited!");
        }
    }

    private void addToFavourites(CrittrUser user, Animal animal) {
        UserAnimalFavourites userAnimalFavourites = new UserAnimalFavourites();
        userAnimalFavourites.setAnimal(animal);
        userAnimalFavourites.setUser(user);
        userAnimalFavouritesService.save(userAnimalFavourites);
    }

    private void removeFromFavourites(CrittrUser user, Animal animal) {
        userAnimalFavouritesService.delete(userAnimalFavouritesService.getByUserAndAnimal(user, animal));
    }

    private List<Animal> getAnimalsFromAnimalFavourites(List<UserAnimalFavourites> userAnimalFavourites){
        List<Animal> animalList = new ArrayList<>();
        userAnimalFavourites.forEach(favourite -> animalList.add(favourite.getAnimal()));
        return animalList;
    }

    private ResponseEntity<?> setLoggedInStatus(AnimalFavouriteResponse response) {
        response.setMessage("Not logged in");
        response.setLoggedIn(false);
        return ResponseEntity.ok(response);
    }
}

