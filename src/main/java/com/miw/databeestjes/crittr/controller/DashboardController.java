package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.model.*;
import com.miw.databeestjes.crittr.service.ReportService;
import com.miw.databeestjes.crittr.service.implementation.CrittrUserDetailsService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Bianca Burema <b.burema@st.hanze.nl>
 * <p>
 * Controller for the dashboard for caretakers
 */

@Controller
public class DashboardController {

    private CrittrUserDetailsService crittrUserDetailsService;
    private ReportService reportService;

    public DashboardController(CrittrUserDetailsService crittrUserDetailsService, ReportService reportService) {
        this.crittrUserDetailsService = crittrUserDetailsService;
        this.reportService = reportService;
    }

    @GetMapping("/account/details/{userId}")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String showCaretakerUserDetails(@PathVariable("userId") long userId, Model model) {
        Optional<CrittrUser> user = crittrUserDetailsService.findById(userId);
        List<Report> newReports = reportService.getByReportStatus(ReportStatus.NEW);
        Collections.reverse(newReports);
        if (user.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("user", user.get());
        model.addAttribute("allCriticalReports", reportService.getByReportPriority(ReportPriority.HIGH));
        model.addAttribute("allMediumReports", reportService.getByReportPriority(ReportPriority.MEDIUM));
        model.addAttribute("allNormalReports", reportService.getByReportPriority(ReportPriority.LOW));
        model.addAttribute("allNewReports",  newReports);
        model.addAttribute("funfact", new FunFact());
        return "caretakerDashboard";
    }
}
