package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.model.FunFact;
import com.miw.databeestjes.crittr.service.FunFactService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

