package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.configuration.ImageUtil;
import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.model.AnimalStatus;
import com.miw.databeestjes.crittr.service.AnimalService;
import com.miw.databeestjes.crittr.service.ReportService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl
 * <p>
 * This class regulates functionalities concerning animals.
 */

@Controller
public class AnimalController {

    private AnimalService animalService;
    private ReportService reportService;

    public AnimalController(AnimalService animalService, ReportService reportService) {
        this.animalService = animalService;
        this.reportService = reportService;
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
        return "caretakerAnimalForm";
    }

    @GetMapping("/animals/details/{animalId}")
    protected String showAnimalDetails(@PathVariable("animalId") long animalId, Model model){
        Optional<Animal> animal = animalService.findByAnimalId(animalId);
        if (animal.isEmpty()){
            return "redirect:/animals";
        }
        model.addAttribute("animal", animal.get());
        model.addAttribute("imageUtil", new ImageUtil());
        return "animalDetails";
    }

    @PostMapping("/animals/new")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String saveUpdateAnimal(@ModelAttribute("animal") @Valid Animal animal , BindingResult result){
        if(result.hasErrors()){
            return "caretakerAnimalForm";
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

    @RequestMapping("/animals/results")
    public String viewAnimalResults(Model model, @Param("keyword") String keyword) {
        List<Animal> foundAnimals = animalService.getAll(keyword);
        model.addAttribute("foundAnimals", foundAnimals);
        model.addAttribute("keyword", keyword);
        return "animalResults";
    }

    @GetMapping("/animals/details/{animalId}/image")
    public String animalImage(@PathVariable Long animalId, HttpServletResponse response) throws IOException {
        Optional<Animal> animal = animalService.findByAnimalId(animalId);
        if (animal.isEmpty()){
            return "redirect:/caretaker/animals";
        }
        byte[] pictureDecompressed = ImageUtil.decompressBytes(animal.get().getPicture());
        response.setContentType("image/jpeg");
        InputStream inputStream = new ByteArrayInputStream(pictureDecompressed);
        IOUtils.copy(inputStream, response.getOutputStream());
        return "caretakerAnimalDetails";
    }

}
