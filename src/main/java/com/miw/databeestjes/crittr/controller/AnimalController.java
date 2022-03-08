package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.model.AnimalStatus;
import com.miw.databeestjes.crittr.model.EduInfo;
import com.miw.databeestjes.crittr.service.AnimalService;
import com.miw.databeestjes.crittr.service.EduInfoService;
import com.miw.databeestjes.crittr.service.FunFactService;
import com.miw.databeestjes.crittr.service.ReportService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl
 * <p>
 * This class regulates functionalities concerning animals.
 */

@Controller
public class AnimalController {

    private static final long ONE_MEGABYTE = 1000000;
    private AnimalService animalService;
    private ReportService reportService;
    private FunFactService funFactService;
    private EduInfoService eduInfoService;

    public AnimalController(AnimalService animalService, ReportService reportService,
                            FunFactService funFactService, EduInfoService eduInfoService) {
        this.animalService = animalService;
        this.reportService = reportService;
        this.funFactService = funFactService;
        this.eduInfoService = eduInfoService;
    }

    @GetMapping("/animals")
    protected String showAnimalOverview (Model model) {
        model.addAttribute("allAnimals", animalService.getAll());
        model.addAttribute("allSpecies", animalService.listSpecies());
        return "animalOverview";
    }

    @GetMapping("/animals/new")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String showAnimalForm (Model model) {
        model.addAttribute("animal", new Animal());
        return "caretakerAnimalForm";
    }

    @GetMapping("/animals/details/{animalId}")
    protected String showAnimalDetails(@PathVariable("animalId") long animalId, Model model){
        Optional<Animal> animal = animalService.findByAnimalId(animalId);
        if (animal.isEmpty()){
            return "redirect:/animals";
        }
        List<EduInfo> eduInfo = eduInfoService.getEduInfoBySpecies(animal.get().getSpecies());
        Collections.sort(eduInfo);
        model.addAttribute("currentAnimalPicture", Base64.getEncoder().encodeToString(animal.get().getAnimalPicture()));
        model.addAttribute("animal", animal.get());
        model.addAttribute("funfact", funFactService.getRandomFact());
        model.addAttribute("eduInfo", eduInfo);
        return "animalDetails";
    }

    @PostMapping("/animals/new")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String saveUpdateAnimal(@ModelAttribute("animal") @Valid Animal animal,
                                      @ModelAttribute("animalPictureInput") MultipartFile animalPictureInput, BindingResult result) {
        if(result.hasErrors()){
            return "caretakerAnimalForm";
        }
        if (!animalPictureInput.isEmpty()){
            setAnimalPicture(animal, animalPictureInput);
        } else {
            animal.setAnimalPicture(animal.getDefaultPicture());
        }
        animalService.save(animal);
        return "redirect:/caretaker/animals";
    }

    private void setAnimalPicture(Animal animal, MultipartFile animalPictureInput) {

        try {
            byte[] imageContent = animalPictureInput.getBytes();
            if (animalPictureInput.getSize() < ONE_MEGABYTE) {
                animal.setAnimalPicture(imageContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/animals/update/{animalId}")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String showAnimalForm(@PathVariable("animalId") long animalId, Model model) {
        Optional<Animal> animal = animalService.findByAnimalId(animalId);
        if (animal.isEmpty()){
            return "redirect:/animals";
        }
        model.addAttribute("animal", animal.get());
        return "caretakerAnimalForm";
    }

    @GetMapping("/caretaker/animals")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String showAnimalOverviewCaretaker (Model model) {
        model.addAttribute("allAnimals", animalService.getAll());
        return "caretakerAnimalOverview";
    }

    @GetMapping("/caretaker/animals/details/{animalId}")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String showAnimalDetailsCaretaker(@PathVariable("animalId") long animalId, Model model){
        Optional<Animal> animal = animalService.findByAnimalId(animalId);
        if (animal.isEmpty()){
            return "redirect:/caretaker/animals";
        }
        Animal certainAnimal = animal.get();
        model.addAttribute("animal", certainAnimal);
        model.addAttribute("allReports", reportService.findByAnimalName(certainAnimal.getName()));
        return "caretakerAnimalDetails";
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
