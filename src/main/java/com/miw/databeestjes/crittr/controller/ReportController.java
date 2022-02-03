package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.model.Report;
import com.miw.databeestjes.crittr.service.AnimalService;
import com.miw.databeestjes.crittr.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Annemiek Blaauwgeers <a.blaauwgeers@st.hanze.nl>
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl>
 * All interactions with Reports.
 */

@Controller
public class ReportController {

    private static final String DEFAULT_REPORT_STATUS = "Pending";
    private static final String REPORT_STATUS_ACCEPTED = "Accepted";
    private static final String REPORT_STATUS_REJECTED = "Discarded";
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
        if(!result.hasErrors() && !report.getSpecies().isEmpty()){
            reportService.save(report);
         }
         return "redirect:/reports";
    }

    @GetMapping("/reports/details/{reportId}")
    protected String showReportDetails(@PathVariable("reportId") long reportId, Model model) {
        Optional<Report> report = reportService.getByReportId(reportId);
        if (report.isEmpty()){
            return "redirect:/reports";
        }
        Report certainReport = report.get();
        if(Objects.isNull(certainReport.getAnimal())) {
            model.addAttribute("animalName", "Unknown");
        } else {
            model.addAttribute("animalName", certainReport.getAnimal().getName());
        }
        model.addAttribute("report", certainReport);

        return "reportDetails";
    }

    @PostMapping("/reports/details/accept/{reportId}")
    protected String acceptReport(@ModelAttribute("report") Report report){
        return getReport(report, REPORT_STATUS_ACCEPTED);
    }

    private String getReport(@ModelAttribute("report") Report report, String reportStatus) {
        Optional<Report> optionalReport = reportService.getByReportId(report.getReportId());
        if(optionalReport.isEmpty()) {
            return "redirect:/reports/details/{" +report.getReportId() + "}";
        }
        Report certainReport = optionalReport.get();
        certainReport.setStatus(reportStatus);
        reportService.save(certainReport);

        return "redirect:/reports";
    }

    @PostMapping("/reports/details/discard/{reportId}")
    protected String discardReport(@ModelAttribute("report") Report report){
        return getReport(report, REPORT_STATUS_REJECTED);
    }
}
