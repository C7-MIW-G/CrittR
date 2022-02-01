package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Report {

    @Id
    @GeneratedValue
    protected long reportId;

    protected String issue;

    @ManyToOne
    protected Animal animal;

    @ManyToOne(optional = false)
    protected Species species;

    @Column
    protected String description;
}
