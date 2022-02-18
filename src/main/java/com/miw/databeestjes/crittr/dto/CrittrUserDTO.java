package com.miw.databeestjes.crittr.dto;

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

/**
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl
 * <p>
 * This class is a dto for CrittrUser objects
 */

@Getter @Setter
public class CrittrUserDTO {

    private CrittrUser user;

    private long userId;

    private String email;

    private String username;

    private String password;

    private UserRoleStatus role;

    private List<Report> reports;

    public CrittrUserDTO(CrittrUser user) {
        this.user = user;
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.reports = user.getReports();
    }
}
