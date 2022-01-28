package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.repository.AnimalRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl
 * <p>
 * This class regulates functionalities concerning animals.
 */

@Controller
public class AnimalController {

    private AnimalRepository animalRepository;

    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @GetMapping("/animals")
    protected String showAnimalOverview (Model model) {
        model.addAttribute("allAnimals", animalRepository.findAll());
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
            animalRepository.save(animal);
        }
        return "redirect:/animals";
    }
}
