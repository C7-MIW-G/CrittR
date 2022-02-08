package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.repository.CrittrUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CrittrUserController {

    CrittrUserRepository crittrUserRepository;
    PasswordEncoder passwordEncoder;

    public CrittrUserController(CrittrUserRepository crittrUserRepository, PasswordEncoder passwordEncoder) {
        this.crittrUserRepository = crittrUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users/new")
    protected String showUserForm(Model model) {
        model.addAttribute("newUser", new CrittrUser());
        return "userForm";
    }

    @PostMapping("/users/new")
    protected String saveUpdateUser(@ModelAttribute("newUser") @Valid CrittrUser user, BindingResult result) {
        if (!result.hasErrors()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            crittrUserRepository.save(user);
            return "redirect:/";
        }
        return "userForm";
    }
}
