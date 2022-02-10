package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.model.AnimalStatus;
import com.miw.databeestjes.crittr.service.AnimalService;
import org.springframework.security.access.annotation.Secured;
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
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
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
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String saveUpdateAnimal(@ModelAttribute("animal") @Valid Animal animal , BindingResult result){
        if(result.hasErrors()){
            return "animalForm";
        }
        animalService.save(animal);
        return "redirect:/caretaker/animals";
    }

    @GetMapping("/animals/update/{animalId}")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String showAnimalForm(@PathVariable("animalId") long animalId, Model model) {
        Optional<Animal> animal = animalService.findByAnimalId(animalId);
        if (animal.isEmpty()){
            return "redirect:/animals";
        }
        model.addAttribute("animal", animal.get());
        return "animalForm";
    }

    @GetMapping("/caretaker/animals")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String showAnimalOverviewCaretaker (Model model) {
        model.addAttribute("allAnimals", animalService.getAll());
        return "animalOverviewCaretaker";
    }

    @GetMapping("/caretaker/animals/details/{animalId}")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String showAnimalDetailsCaretaker(@PathVariable("animalId") long animalId, Model model){
        Optional<Animal> animal = animalService.findByAnimalId(animalId);
        if (animal.isEmpty()){
            return "redirect:/caretaker/animals";
        }
        model.addAttribute("animal", animal.get());
        return "animalDetailsCaretaker";
    }

    private String getAnimal(@ModelAttribute("animal") Animal animal, AnimalStatus status){
        Optional<Animal> optionalAnimal = animalService.findByAnimalId(animal.getAnimalId());
        if (optionalAnimal.isEmpty()){
            return "redirect:/caretaker/animals/details/" + animal.getAnimalId();
        }
        Animal certainAnimal = optionalAnimal.get();
        certainAnimal.setStatus(status);
        animalService.save(certainAnimal);
        return "redirect:/caretaker/animals";
    }

    @PostMapping("/caretaker/animals/details/incoming/{animalId}")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String setToIncoming(@ModelAttribute("animal") Animal animal){
        return getAnimal(animal, AnimalStatus.INCOMING);
    }

    @PostMapping("/caretaker/animals/details/present/{animalId}")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String setToPresent(@ModelAttribute("animal") Animal animal){
        return getAnimal(animal, AnimalStatus.PRESENT);
    }

    @PostMapping("/caretaker/animals/details/relocated/{animalId}")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String setToRelocated(@ModelAttribute("animal") Animal animal){
        return getAnimal(animal, AnimalStatus.RELOCATED);
    }

    @PostMapping("/caretaker/animals/details/deceased/{animalId}")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String setToDeceased(@ModelAttribute("animal") Animal animal){
        return getAnimal(animal, AnimalStatus.DECEASED);
    }

}
