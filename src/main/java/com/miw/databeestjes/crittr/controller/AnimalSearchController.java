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
import java.util.stream.Collectors;

/**
 * @author Ivo Didden <i.l.didden@st.hanze.nl>
 * <p>
 * This file is part of our Crittr Project
 */
@RestController
public class AnimalSearchController {

    AnimalService animalService;
    UserAnimalFavouritesService userAnimalFavouritesService;

    public AnimalSearchController(AnimalService animalService, UserAnimalFavouritesService userAnimalFavouritesService) {
        this.animalService = animalService;
        this.userAnimalFavouritesService = userAnimalFavouritesService;
    }

    @PostMapping("/api/animals/search")
    protected ResponseEntity<?> showAnimalsFound(@Valid @RequestBody AnimalCriteria keywords, Errors errors,
                                                 @AuthenticationPrincipal CrittrUser user) {
        AnimalSearchResponse response = new AnimalSearchResponse();

        if (errors.hasErrors()) {
            response.setMsg(errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(response);
        }

        List<Animal> animalList;

        if(keywords.getStatus() == null){
            animalList = animalService.getAll(keywords.getKeyword());
        } else if(keywords.getKeyword().equals("")) {
            animalList = animalService.listByStatus(keywords.getStatus());
        } else {
            animalList = animalService.getBySpeciesAndStatus(keywords.getKeyword(), keywords.getStatus());
        }
        List<AnimalDTO> animalDTOS = new ArrayList<>();
        setResponseData(animalDTOS, animalList, response);
        setFavouritedStatus(user, response);

        return ResponseEntity.ok(response);
    }

    public void setResponseData(List<AnimalDTO> animalDTOS, List<Animal> animals, AnimalSearchResponse response) {
        animals.forEach(animal -> animalDTOS.add(new AnimalDTO(animal)));
        if(animalDTOS.isEmpty()){
            response.setMsg("No animals found");
        } else {
            response.setMsg("Animals found");
        }
        response.setDtos(animalDTOS);
    }

    public void setFavouritedStatus(CrittrUser user,
                                    AnimalSearchResponse response){
        if(user != null){
            List<Long> animalIds = getIdsFromFavourites(userAnimalFavouritesService.getByUser(user));
            response.getDtos().forEach(dto -> setFavourites(animalIds, dto));
        }
    }

    private void setFavourites(List<Long> animalIds, AnimalDTO dto) {
        dto.setFavourited(animalIds.contains(dto.getAnimalId()));
    }

    private List<Long> getIdsFromFavourites (List<UserAnimalFavourites> favourites){
        List<Long> animalIds = new ArrayList<>();

        favourites.forEach(favourite -> animalIds.add(favourite.getAnimal().getAnimalId()));
        return animalIds;
    }

}
