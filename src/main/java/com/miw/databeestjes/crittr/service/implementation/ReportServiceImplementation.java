package com.miw.databeestjes.crittr.service.implementation;

import com.miw.databeestjes.crittr.model.*;
import com.miw.databeestjes.crittr.repository.ReportRepository;
import com.miw.databeestjes.crittr.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImplementation implements ReportService {

    public static final long INITIAL_REPORT_NUMBER = 10000;
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

    @Override
    public Optional<Report> getByReportId(long reportId) {
        return reportRepository.findByReportId(reportId);
    }

    @Override
    public List<Report> findByAnimalName(String animalName) {
        return reportRepository.findByAnimalName(animalName);
    }

    @Override
    public List<Report> findByAnimal(Animal animal) {
        return reportRepository.findByAnimal(animal);
    }

    public long getNextNumber() {
        Optional<Long> lastNumber = reportRepository.lastReport();
        if(lastNumber.isEmpty()) {
            return INITIAL_REPORT_NUMBER;
        }
        return getNextValue(lastNumber.get());
    }

    @Override
    public void addNew(String issue, Animal animal, String animalName, String species, String description,
                       CrittrUser reporter, ReportPriority... priority) {
        Report report = new Report();
        report.setIssue(issue);
        report.setAnimal(animal);
        report.setAnimalName(animalName);
        report.setSpecies(species);
        report.setDescription(description);
        report.setReporter(reporter);

        if(priority.length > 0) {
            report.setPriority(priority[0]);
        }

        report.setReportNumber(getNextNumber());
        reportRepository.save(report);
    }

    private long getNextValue(long lastNumber) {
        if(lastNumber < INITIAL_REPORT_NUMBER) {
            return INITIAL_REPORT_NUMBER;
        } else {
            return ++lastNumber;
        }
    }

    public Optional<Report> getByReportNumber(long reportNumber) {
        return reportRepository.findByReportNumber(reportNumber);
    }

}
