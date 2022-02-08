package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;


@Entity
@Getter @Setter
public class Report {

    private static final String DEFAULT_REPORT_STATUS = "New";
    @Id
    @GeneratedValue
    private long reportId;

    @NotEmpty
    private String issue;

    private String status = DEFAULT_REPORT_STATUS;

    private String animalName;

    @Column(nullable = false)
    @NotEmpty
    private String species;

    @Column
    private String description;

    private LocalDateTime reportDate = LocalDateTime.now();


}
