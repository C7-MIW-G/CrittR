package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.dto.AnimalDTO;
import com.miw.databeestjes.crittr.model.*;
import com.miw.databeestjes.crittr.service.AnimalService;
import org.springframework.http.ResponseEntity;
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

    public AnimalSearchController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping("/api/animals/search")
    protected ResponseEntity<?> showAnimalsFound(@Valid @RequestBody AnimalCriteria keyword, Errors errors) {
        AnimalSearchResponse response = new AnimalSearchResponse();

        if (errors.hasErrors()) {
            response.setMsg(errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(response);
        }

        List<Animal> animalList = animalService.getAll(keyword.getKeyword());
        List<AnimalDTO> animalDTOS = new ArrayList<>();
        setResponseData(animalDTOS, animalList, response);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/api/animals/search-status")
    protected ResponseEntity<?> showAnimalsFoundByStatus(@Valid @RequestBody AnimalCriteria status, Errors errors) {
        AnimalSearchResponse response = new AnimalSearchResponse();
        if (errors.hasErrors()) {
            response.setMsg(errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(response);
        }
        List<Animal> animalList = animalService.listByStatus(status.getStatus());
        List<AnimalDTO> animalDTOS = new ArrayList<>();
         setResponseData(animalDTOS, animalList, response);
        return ResponseEntity.ok(response);
    }

    public void setResponseData(List<AnimalDTO> animalDTOS, List<Animal> animals, AnimalSearchResponse response) {
        for (Animal animal : animals) {
            animalDTOS.add(new AnimalDTO(animal));
        }
        if(animalDTOS.isEmpty()){
            response.setMsg("No animals found");
        } else {
            response.setMsg("Animals found");
        }
        response.setDtos(animalDTOS);
    }

}
