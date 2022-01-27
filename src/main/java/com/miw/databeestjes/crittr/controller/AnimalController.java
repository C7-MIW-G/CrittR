package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.repository.AnimalRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
