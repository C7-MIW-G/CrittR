package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.repository.AnimalRepository;
import com.miw.databeestjes.crittr.service.AnimalService;
import com.miw.databeestjes.crittr.service.SpeciesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl
 * <p>
 * This class regulates functionalities concerning animals.
 */

@Controller
public class AnimalController {

    private AnimalService animalService;
    private SpeciesService speciesService;

    public AnimalController(AnimalService animalService, SpeciesService speciesService) {
        this.animalService = animalService;
        this.speciesService = speciesService;
    }

    @GetMapping("/animals")
    protected String showAnimalOverview (Model model) {
        model.addAttribute("allAnimals", animalService.getAll());
        return "animalOverview";
    }

    @GetMapping("/animals/new")
    protected String showAnimalForm (Model model) {
        model.addAttribute("animal", new Animal());
        model.addAttribute("allSpecies", speciesService.getAll());
        return "animalForm";
    }

    @GetMapping("/animals/details/{animalId}")
    protected String showAnimalDetails(@PathVariable("animalId") long animalId, Model model){
        Optional<Animal> animal = animalService.findByAnimalId(animalId);
        if (animal.isEmpty()){
            return "redirect:/animals";
        }
        model.addAttribute("animal", animal.get());
        return "animalDetails";
    }


    @PostMapping("/animals/new")
    protected String saveUpdateAnimal(@ModelAttribute("animal") Animal animal , BindingResult result){
        if(!result.hasErrors()){
            animalService.save(animal);
        }
        return "redirect:/animals";
    }

    @GetMapping("/animals/update/{animalId}")
    protected String showAnimalForm(@PathVariable("animalId") long animalId, Model model) {
        Optional<Animal> animal = animalService.findByAnimalId(animalId);
        if (animal.isEmpty()){
            return "redirect:/animals";
        }
        model.addAttribute("animal", animal.get());
        model.addAttribute("allSpecies", speciesService.getAll());
        return "animalForm";
    }

    @GetMapping("animals/delete/{animalId}")
    protected String deleteAnimal(@PathVariable("animalId") long animalId, Model model) {
        Animal animal = animalService.findByAnimalId(animalId).orElseThrow(()
                -> new IllegalArgumentException("invalid animal id: " + animalId));
        animalService.delete(animal);
        return "redirect:/animals";
    }
}
