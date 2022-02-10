package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.model.Report;
import com.miw.databeestjes.crittr.model.ReportStatus;
import com.miw.databeestjes.crittr.service.ReportService;
import com.miw.databeestjes.crittr.service.implementation.CrittrUserDetailsService;
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
        return "reportOverview";
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
        return "redirect:/reports";
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

        return "reportDetails";
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
}
