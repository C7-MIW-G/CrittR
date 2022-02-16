package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter @Setter
public class Report {

    @Id
    @GeneratedValue
    private long reportId;

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
}
