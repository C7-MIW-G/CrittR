package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.model.Report;
import com.miw.databeestjes.crittr.model.ReportStatus;
import com.miw.databeestjes.crittr.service.AnimalService;
import com.miw.databeestjes.crittr.service.ReportService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

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
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String showReportOverview (Model model) {
        model.addAttribute("allReports", reportService.getAll());
        return "caretakerReportOverview";
    }

    @GetMapping("reports/new")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN", "ROLE_MEMBER"})
    protected String showReportForm (Model model) {
        model.addAttribute("allAnimals", animalService.getAll());
        model.addAttribute("report", new Report());
        return "reportForm";
    }

    @PostMapping("reports/new")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN", "ROLE_MEMBER"})
    protected String createUpdateReport(@ModelAttribute("report") @Valid Report report, BindingResult result,
                                        @AuthenticationPrincipal CrittrUser user){
        if(result.hasErrors()){
            return "reportForm";
        }
        report.setReporter(user);
        report.setReportNumber(reportService.getNextNumber());
        reportService.save(report);
        return "redirect:/user/details/" + user.getUserId();
    }

    @PostMapping("/reports/claim/{reportNr}")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String claimReport(@PathVariable("reportNr") long reportNr,
                                 @AuthenticationPrincipal CrittrUser crittrUser){
        Optional<Report> report = reportService.getByReportNumber(reportNr);
        if(report.isEmpty()){
            return "redirect:/reports";
        }
        Report certainReport = report.get();
        certainReport.setClaimer(crittrUser);
        reportService.save(certainReport);
        return "redirect:/reports/ct/details/" + reportNr;
    }

    @GetMapping("/reports/details/{reportNr}")
    @Secured("ROLE_MEMBER")
    protected String showReportDetails(@PathVariable("reportNr") Long reportNr, Model model) {
        Optional<Report> report = reportService.getByReportNumber(reportNr);
        if (report.isEmpty()) {
            return "userDetails";
        }
        Report certainReport = report.get();
        if(certainReport.getAnimalName().equals("")) {
            model.addAttribute("animalName", "Unknown");
        } else {
            model.addAttribute("animalName", certainReport.getAnimalName());
        }
        model.addAttribute("report", certainReport);
        return "userReportDetails";
    }

    @GetMapping("/reports/ct/details/{reportNr}")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String showCaretakerReportDetails(@PathVariable("reportNr") long reportNr, Model model) {
        Optional<Report> report = reportService.getByReportNumber(reportNr);
        if (report.isEmpty()){
            return "redirect:/reports";
        }
        Report certainReport = report.get();
        if(certainReport.getAnimalName().equals("")) {
            model.addAttribute("animalName", "Unknown");
        } else {
            model.addAttribute("animalName", certainReport.getAnimalName());
        }
        if(certainReport.getClaimer() == null){
            model.addAttribute("claimer", "No one yet");
        } else {
            model.addAttribute("claimer", certainReport.getClaimer().getEmail());
        }
        model.addAttribute("report", certainReport);
        return "caretakerReportDetails";
    }

    private String getReport(@ModelAttribute("report") Report report, ReportStatus reportStatus) {
        Optional<Report> optionalReport = reportService.getByReportId(report.getReportId());
        if(optionalReport.isEmpty()) {
            return "redirect:/reports/ct/details/" + report.getReportNumber();
        }
        Report certainReport = optionalReport.get();
        certainReport.setStatus(reportStatus);
        reportService.save(certainReport);
        return "redirect:/reports";
    }

    @PostMapping("/reports/details/accept/{reportId}")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String acceptReport(@ModelAttribute("report") Report report){
        return getReport(report, ReportStatus.OPEN_ISSUE);
    }


    @PostMapping("/reports/details/discard/{reportId}")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String discardReport(@ModelAttribute("report") Report report){
        return getReport(report, ReportStatus.DISCARDED);
    }

    @PostMapping("/reports/details/observation/{reportId}")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String monitorReport(@ModelAttribute("report") Report report){
        return getReport(report, ReportStatus.UNDER_OBSERVATION);
    }

    @PostMapping("/reports/details/closed/{reportId}")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String closeReport(@ModelAttribute("report") Report report){
        return getReport(report, ReportStatus.CLOSED);
    }

    @GetMapping("/reports/details/edit/{reportNumber}")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String showEditReportForm (@PathVariable("reportNumber") long reportnr, Model model) {
        Optional<Report> optionalReport = reportService.getByReportNumber(reportnr);
        if(optionalReport.isPresent()){
            model.addAttribute("report", optionalReport.get());
            return "caretakerReportEditForm";
        }
        return "redirect:/";
    }

    @PostMapping("/reports/details/edit/{reportNumber}")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String updateReport(@ModelAttribute("report") Report report, BindingResult result){
        if(!result.hasErrors()){
            changeName(report);
            reportService.save(report);
        }
        return "redirect:/reports/ct/details/" + report.getReportNumber();
    }

    private void changeName(Report report) {
        if(report.getAnimal() != null) {
            report.setAnimalName(report.getAnimal().getName());
        }
    }
}
