package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.repository.CrittrUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class CrittrUserController {

    public static final String EXISTING_EMAIL = "This email is already in use";
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

    protected String showUserFormWithError(Model model) {
        model.addAttribute("newUser", new CrittrUser());
        model.addAttribute("uniquenessError", EXISTING_EMAIL);
        return "userForm";
    }

    @PostMapping("/users/new")
    protected String saveUpdateUser(@ModelAttribute("newUser") @Valid CrittrUser user, BindingResult result, Model model) {
        List<CrittrUser> userList = crittrUserRepository.listByEmail(user.getEmail());
        if(userList.size() > 0) {
            return showUserFormWithError(model);
        }
        if (!result.hasErrors()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            crittrUserRepository.save(user);
            return "redirect:/";
        }
        return "userForm";
    }

    @GetMapping("user/details/{userId}")
    protected String showUserDetails (@PathVariable("userId") long userId, Model model) {
        Optional<CrittrUser> user = crittrUserRepository.findById(userId);
        if(user.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("user", user.get());
        return "userDetails";
    }
}
