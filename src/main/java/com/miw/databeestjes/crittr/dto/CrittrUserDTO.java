package com.miw.databeestjes.crittr.dto;

import com.miw.databeestjes.crittr.model.CrittrUser;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl
 * <p>
 * This class is a dto for CrittrUser objects
 */

@Getter @Setter
public class CrittrUserDTO {

    private long userId;

    private String email;

    private String username;

    private String role;

    public CrittrUserDTO(CrittrUser user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.role = user.getRole().getDisplayName();
    }
}
