package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.access.annotation.Secured;

/**
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl
 * <p>
 * This class configures search data for an ajax request
 */

@Getter @Setter
public class CrittrUserCriteria {
    String email;

    UserRoleStatus role;

}
