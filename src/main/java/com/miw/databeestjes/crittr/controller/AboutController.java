package com.miw.databeestjes.crittr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Ivo Didden <i.l.didden@st.hanze.nl>
 * <p>
 * This class regulates access to the About page
 */

@Controller
public class AboutController {

    @GetMapping({ "/about"})
    protected String showAboutPage () {
        return "about";
    }
}
