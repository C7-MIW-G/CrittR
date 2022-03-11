package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.model.FunFact;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl
 * <p>
 * This class regulates access to the homepage
 */

@Controller
public class HomepageController {

    @GetMapping({"/", "/home"})
    protected String showHomePage (Model model) {
        model.addAttribute("funfact", new FunFact());
        return "index";
    }
}
