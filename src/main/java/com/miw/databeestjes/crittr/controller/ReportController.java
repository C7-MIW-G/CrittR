package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.model.Report;
import com.miw.databeestjes.crittr.model.ReportStatus;
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

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
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
        reportService.save(report);
        return "redirect:/user/details/" + user.getUserId();
    }

    @GetMapping("/reports/details/{reportId}")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN", "ROLE_MEMBER"})
    protected String showReportDetails(@PathVariable("reportId") long reportId, Model model) {
        Optional<Report> report = reportService.getByReportId(reportId);
        if (report.isEmpty()){
            return "redirect:/reports";
        }
        Report certainReport = report.get();
        if(certainReport.getAnimalName().equals("")) {
            model.addAttribute("animalName", "Unknown");
        } else {
            model.addAttribute("animalName", certainReport.getAnimalName());
        }
        model.addAttribute("report", certainReport);

        return "caretakerReportDetails";
    }

    @PostMapping("/reports/details/accept/{reportId}")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String acceptReport(@ModelAttribute("report") Report report){
        return getReport(report, ReportStatus.OPEN_ISSUE);
    }

    private String getReport(@ModelAttribute("report") Report report, ReportStatus reportStatus) {
        Optional<Report> optionalReport = reportService.getByReportId(report.getReportId());
        if(optionalReport.isEmpty()) {
            return "redirect:/reports/details/" + report.getReportId();
        }
        Report certainReport = optionalReport.get();
        certainReport.setStatus(reportStatus);
        reportService.save(certainReport);

        return "redirect:/reports";
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

    @PostMapping("/reports/details/add/animal/{reportId}")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String addAnimalToReport(@ModelAttribute("linkedAnimal") Animal animal,
                                       @ModelAttribute("report") Report report,
                                       BindingResult result){
        if(!result.hasErrors()){
            report.setAnimal(animal);
            report.setAnimalName(animal.getName());
             reportService.save(report);
        }
         return "redirect:/reports/details/" + report.getReportId();
    }

    @GetMapping("/reports/details/edit/{reportId}")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String showEditReportForm (@PathVariable("reportId") long reportId, Model model) {
        Optional<Report> optionalReport = reportService.getByReportId(reportId);
        if(optionalReport.isPresent()){
            model.addAttribute("report", optionalReport.get());
            return "caretakerEditReportForm";
        }
        return "redirect:/";
    }

    @PostMapping("/reports/details/edit/{reportId}")
    @Secured({"ROLE_CARETAKER", "ROLE_ADMIN"})
    protected String updateReport(@ModelAttribute("report") Report report , BindingResult result){
        if(!result.hasErrors()){
            reportService.save(report);
         }
        return "redirect:/reports/details" + report.getReportId();
    }
}
