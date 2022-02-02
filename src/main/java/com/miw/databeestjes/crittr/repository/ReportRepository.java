package com.miw.databeestjes.crittr.repository;

import com.miw.databeestjes.crittr.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findByReportId(long reportId);
}
