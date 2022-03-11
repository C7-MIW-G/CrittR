package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.model.FunFact;
import com.miw.databeestjes.crittr.model.Report;
import com.miw.databeestjes.crittr.service.FunFactService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * @author Ivo Didden <i.l.didden@st.hanze.nl>
 * <p>
 * This file is part of our Crittr Project
 */
@Controller
public class FunFactController {

    FunFactService funFactService;

    public FunFactController(FunFactService funFactService) {
        this.funFactService = funFactService;
    }

    @PostMapping("/facts/new")
    protected String addFunFact(@ModelAttribute("funfact") FunFact funfact, BindingResult result){
        if(result.hasErrors()){
        return "redirect:/" ;
        }
        funFactService.addFunFact(funfact.getFact(), funfact.getSpecies());
        return "redirect:/";
     }

}

