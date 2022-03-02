package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.service.AnimalService;
import com.miw.databeestjes.crittr.service.implementation.CrittrUserDetailsService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
public class CrittrUserController {

    public static final String EXISTING_EMAIL = "This email is already in use";

    PasswordEncoder passwordEncoder;
    CrittrUserDetailsService crittrUserDetailsService;
    AnimalService animalService;

    public CrittrUserController(PasswordEncoder passwordEncoder, CrittrUserDetailsService crittrUserDetailsService,
                                AnimalService animalService) {
        this.passwordEncoder = passwordEncoder;
        this.crittrUserDetailsService = crittrUserDetailsService;
        this.animalService = animalService;
    }

    @GetMapping("/users/new")
    protected String showUserForm(Model model) {
        model.addAttribute("user", new CrittrUser());
        return "userForm";
    }

    protected String showUserFormWithError(Model model) {
        model.addAttribute("user", new CrittrUser());
        model.addAttribute("uniquenessError", EXISTING_EMAIL);
        return "userForm";
    }

    @PostMapping("/users/new")
    protected String saveUser(@ModelAttribute("user") @Valid CrittrUser user, BindingResult result, Model model) {
        List<CrittrUser> userList = crittrUserDetailsService.listByEmail(user.getEmail());
        if (userList.size() > 0) {
            return showUserFormWithError(model);
        }
        if (result.hasErrors()) {
            return "userForm";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        crittrUserDetailsService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/user/details/{userId}")
    @Secured({"ROLE_CARETAKER", "ROLE_MEMBER", "ROLE_ADMIN"})
    protected String showUserDetails(@PathVariable("userId") long userId, Model model) {
        Optional<CrittrUser> user = crittrUserDetailsService.findById(userId);
        if (user.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("user", user.get());
        return "userDetails";
    }

    @GetMapping("/user/details/edit/{userId}")
    @Secured({"ROLE_CARETAKER", "ROLE_MEMBER", "ROLE_ADMIN"})
    protected String showUserForm(@PathVariable("userId") long userId, Model model) {
        Optional<CrittrUser> user = crittrUserDetailsService.findById(userId);
        if (user.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("user", user.get());
        return "userEditForm";
    }

    @PostMapping("/user/details/edit/{userId}")
    @Secured({"ROLE_CARETAKER", "ROLE_MEMBER", "ROLE_ADMIN"})
    protected String updateUser(@ModelAttribute("user") @Valid CrittrUser user, BindingResult result) {

        if (!result.hasErrors()) {
            return crittrUserDetailsService.saveWithPassword(user, passwordEncoder.encode(user.getPassword()));
        }
        if (user.getPassword().equals("")
                && !user.getUsername().equals("")
                && !user.getEmail().equals("")) {
            return crittrUserDetailsService.saveWithoutPassword(user);
        }
        return "userEditForm";
    }

    @GetMapping("/user/details/delete/{userId}")
    @Secured({"ROLE_CARETAKER", "ROLE_MEMBER", "ROLE_ADMIN"})
    protected String deleteUser(@PathVariable("userId") long userId) {
        Optional<CrittrUser> userOptional = crittrUserDetailsService.findById(userId);
        if (userOptional.isEmpty()) {
            return "userDetails";
        }
        CrittrUser crittrUser = userOptional.get();
        crittrUserDetailsService.delete(crittrUser.getUserId());
        logout();
        return "redirect:/";
    }

    public void logout() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder
                        .getRequestAttributes())
                        .getRequest();
        new SecurityContextLogoutHandler().logout(request, null, null);
    }

    @GetMapping("/accounts/users")
    @Secured("ROLE_ADMIN")
    protected String showAllAccounts(Model model) {
        model.addAttribute("allAccounts", crittrUserDetailsService.findAll());
        return "adminAccountOverview";
    }

    @RequestMapping("/animals/addFavourite")
    public String addFavouriteAnimal(@PathVariable("animalId") Long animalId, CrittrUser user) {
        Optional<Animal> animal = animalService.findByAnimalId(animalId);
        if (animal.isEmpty()) {
            return "redirect:/animals";
        }
        Set<Animal> animals = new HashSet<>();
        animals.add(animal.get());
        user.setFavouriteAnimals(animals);
        return "redirect:/animals";
    }
}
