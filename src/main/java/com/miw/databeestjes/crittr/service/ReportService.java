package com.miw.databeestjes.crittr.service;

import com.miw.databeestjes.crittr.model.Report;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReportService {

    List<Report> getAll();

    void save(Report report);

    Optional<Report> getByReportId(long reportId);

    List<Report> findByAnimalName(String animalName);
}
