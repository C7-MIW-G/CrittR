package com.miw.databeestjes.crittr.service;

import com.miw.databeestjes.crittr.model.Report;

import java.util.List;

public interface ReportService {

    List<Report> getAll();

    void save(Report report);
}
