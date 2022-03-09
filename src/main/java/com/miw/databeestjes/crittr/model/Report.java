package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Report {

    @Id
    @GeneratedValue
    private long reportId;

    @Column(unique = true)
    private long reportNumber;

    @NotEmpty
    private String issue;

    private ReportStatus status = ReportStatus.NEW;

    private String animalName;

    @Column(nullable = false)
    @NotEmpty
    private String species;

    @Column
    private String description;

    private LocalDateTime reportDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(nullable = false)
    private CrittrUser reporter;

    private ReportPriority priority = ReportPriority.LOW;

    @ManyToOne
    @JoinColumn(nullable = true)
    private CrittrUser claimer;

    @ManyToOne
    private Animal animal;

}
