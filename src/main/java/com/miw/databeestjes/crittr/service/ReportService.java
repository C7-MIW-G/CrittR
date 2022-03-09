package com.miw.databeestjes.crittr.service;

import com.miw.databeestjes.crittr.model.*;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReportService {

    List<Report> getAll();

    void save(Report report);

    Optional<Report> getByReportId(long reportId);

    Optional<Report> getByReportNumber(long reportNumber);

    List<Report> findByAnimalName(String animalName);

    List<Report> findByAnimal(Animal animal);

    long getNextNumber();

    void addNew(String issue, Animal animal, String animalName, String species, String description, CrittrUser reporter, ReportPriority... priority);
}
