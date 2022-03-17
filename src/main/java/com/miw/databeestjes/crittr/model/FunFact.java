package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @author Ivo Didden <i.l.didden@st.hanze.nl>
 * <p>
 * This class describes a fun fact
 */
@Entity
@Getter @Setter
public class FunFact {

    @Id @Column (length = 500)
    private String fact;

    @NotNull
    private String species;

}
