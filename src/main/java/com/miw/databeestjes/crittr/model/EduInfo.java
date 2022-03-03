package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @author Ivo Didden <i.l.didden@st.hanze.nl>
 * <p>
 * This file is part of our Crittr Project
 */

@Entity
@Getter @Setter
public class EduInfo implements Comparable<EduInfo> {

    @Id
    @GeneratedValue
    protected long eduInfoId;

    @Column(length = 750)
    private String eduInfo;

    @NotNull
    private String eduInfoSpecies;

    @NotNull
    private EduInfoCategory infoCategory;

    @Override
    public int compareTo(EduInfo otherEduInfo) {
        if (this.infoCategory.ordinal() > otherEduInfo.infoCategory.ordinal())
            return 1;
        else if (this.infoCategory.ordinal() == otherEduInfo.infoCategory.ordinal())
            return 0;
        else return -1;
    }
}

