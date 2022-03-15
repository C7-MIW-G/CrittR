package com.miw.databeestjes.crittr.repository;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.model.Report;
import com.miw.databeestjes.crittr.model.ReportPriority;
import com.miw.databeestjes.crittr.model.ReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findByReportId(long reportId);

    Optional<Report> findByReportNumber(long reportNumber);

    @Query("SELECT r FROM Report r WHERE r.animalName = ?1")
    List<Report> findByAnimalName(String animalName);

    @Query("SELECT r FROM Report r WHERE r.animal = ?1")
    List<Report> findByAnimal(Animal animal);

    @Query("SELECT MAX (r.reportNumber) FROM Report r")
    Optional<Long> lastReport();

    @Query("SELECT r FROM Report r WHERE r.priority = ?1")
    List<Report> findByReportPriority(ReportPriority priority);

    @Query("SELECT r FROM Report r WHERE r.status = ?1")
    List<Report> findByReportStatus(ReportStatus status);
}
