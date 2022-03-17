package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * <p>
 * This class describes a report.
 */

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

    private String animalName = setDefaultName();

    @Column(nullable = false)
    @NotEmpty
    private String species;

    @Column(length = 750)
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

    private String setDefaultName() {
        if(this.animal == null) {
            return "";
        } else {
            return this.animal.getName();
        }
    }

}
