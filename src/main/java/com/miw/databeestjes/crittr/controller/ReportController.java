package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.model.Report;
import com.miw.databeestjes.crittr.repository.ReportRepository;
import com.miw.databeestjes.crittr.service.AnimalService;
import com.miw.databeestjes.crittr.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Annemiek Blaauwgeers <a.blaauwgeers@st.hanze.nl>
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl>
 * All interactions with Reports.
 */

@Controller
public class ReportController {

    private ReportService reportService;
    private AnimalService animalService;

    public ReportController(ReportService reportService, AnimalService animalService) {
        this.reportService = reportService;
        this.animalService = animalService;
    }

    @GetMapping("/reports")
    protected String showReportOverview (Model model) {
        model.addAttribute("allReports", reportService.getAll());
        return "reportOverview";
    }

    @GetMapping("reports/new")
    protected String showReportForm (Model model) {
        model.addAttribute("report", new Report());
        model.addAttribute("allAnimals", animalService.getAll());
        return "reportForm";
    }

    @PostMapping("reports/new")
    protected String createUpdateReport(@ModelAttribute("report") Report report, BindingResult result){
        if(!result.hasErrors()){
            reportService.save(report);
         }
         return "redirect:/reports";
    }

}
