package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.repository.AnimalRepository;
import com.miw.databeestjes.crittr.service.AnimalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        return "animalForm";
    }

}
