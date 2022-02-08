package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.service.AnimalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl
 * <p>
 * This class regulates functionalities concerning animals.
 */

@Controller
public class AnimalController {

    private AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/animals")
    protected String showAnimalOverview (Model model) {
        model.addAttribute("allAnimals", animalService.getAll());
        return "animalOverview";
    }

    @GetMapping("/animals/new")
    protected String showAnimalForm (Model model) {
        model.addAttribute("animal", new Animal());
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
    protected String saveUpdateAnimal(@ModelAttribute("animal") @Valid Animal animal , BindingResult result){
        if(!result.hasErrors()
        && !animal.getName().isEmpty()
        && !animal.getSpecies().isEmpty()
        && animal.getBirthDate() != null){
            animalService.save(animal);
        } else {
            return "animalForm";
        }
        return "redirect:/caretaker/animals";
    }

    @GetMapping("/animals/update/{animalId}")
    protected String showAnimalForm(@PathVariable("animalId") long animalId, Model model) {
        Optional<Animal> animal = animalService.findByAnimalId(animalId);
        if (animal.isEmpty()){
            return "redirect:/animals";
        }
        model.addAttribute("animal", animal.get());
        return "animalForm";
    }

    @GetMapping("animals/delete/{animalId}")
    protected String deleteAnimal(@PathVariable("animalId") long animalId) {
        Animal animal = animalService.findByAnimalId(animalId).orElseThrow(()
                -> new IllegalArgumentException("invalid animal id: " + animalId));
        animalService.delete(animal);
        return "redirect:/animals";
    }

    @GetMapping("/caretaker/animals")
    protected String showAnimalOverviewCaretaker (Model model) {
        model.addAttribute("allAnimals", animalService.getAll());
        return "animalOverviewCaretaker";
    }

    @GetMapping("/caretaker/animals/details/{animalId}")
    protected String showAnimalDetailsCaretaker(@PathVariable("animalId") long animalId, Model model){
        Optional<Animal> animal = animalService.findByAnimalId(animalId);
        if (animal.isEmpty()){
            return "redirect:/caretaker/animals";
        }
        model.addAttribute("animal", animal.get());
        return "animalDetailsCaretaker";
    }


}
