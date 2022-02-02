package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter @Setter
public class Report {

    @Id
    @GeneratedValue
    private long reportId;

    private String issue;

    @ManyToOne
    private Animal animal;

    @Column(nullable = false)
    private String species;

    @Column
    private String description;

    private LocalDateTime reportDate = LocalDateTime.now();


}
