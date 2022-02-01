package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

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

    @Column(nullable = true)
    protected String description;
}
