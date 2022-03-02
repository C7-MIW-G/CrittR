package com.miw.databeestjes.crittr.dto;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.model.Report;
import com.miw.databeestjes.crittr.model.UserRoleStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    private Set<Animal> favouriteAnimals;

    public CrittrUserDTO(CrittrUser user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.role = user.getRole().getDisplayName();
        this.favouriteAnimals = user.getFavouriteAnimals();
    }
}
