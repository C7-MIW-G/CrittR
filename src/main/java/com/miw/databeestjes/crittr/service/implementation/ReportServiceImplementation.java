package com.miw.databeestjes.crittr.service.implementation;

import com.miw.databeestjes.crittr.model.Report;
import com.miw.databeestjes.crittr.repository.ReportRepository;
import com.miw.databeestjes.crittr.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImplementation implements ReportService {

    ReportRepository reportRepository;

    public ReportServiceImplementation(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public List<Report> getAll() {
        return reportRepository.findAll();
    }

    @Override
    public void save(Report report) {
        reportRepository.save(report);
    }
}
