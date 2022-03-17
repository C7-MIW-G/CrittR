package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Ivo Didden <i.l.didden@st.hanze.nl>
 * <p>
 * This is an entity for our user comment system.
 */

@Entity
@Getter @Setter
public class Comment {

    @Id
    @GeneratedValue
    private long commentId;

    @ManyToOne
    private CrittrUser commenter;

    @ManyToOne
    private Animal animal;

    @NotNull
    @Column(length = 750)
    private String commentText;

    @NotNull
    private LocalDateTime dateTime = LocalDateTime.now();

}
