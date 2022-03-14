package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Ivo Didden <i.l.didden@st.hanze.nl>
 * <p>
 * This file is part of our Crittr Project
 */

@Entity
@Getter @Setter
public class Comment {

    @Id
    @GeneratedValue
    protected long commentId;

    @ManyToOne
    protected CrittrUser commenter;

    @ManyToOne
    protected Animal animal;

    @NotNull
    protected String commentText;

    @NotNull
    protected LocalDateTime dateTime = LocalDateTime.now();

}
