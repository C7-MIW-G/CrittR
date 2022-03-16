package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl
 * <p>
 * This class describes a user in general
 */
@Entity
@Getter @Setter
public class CrittrUser implements UserDetails {

    @Id
    @GeneratedValue
    private long userId;

    @Column(unique = true)
    @Email(regexp = ".+@.+\\..+")
    private String email;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    private UserRoleStatus role = UserRoleStatus.ROLE_MEMBER;

    @OneToMany(mappedBy = "reporter")
    private List<Report> reports;

    @OneToMany(mappedBy = "user")
    private Set<UserAnimalFavourites> likes;

    @OneToMany(mappedBy = "claimer")
    private List<Report> claimedReports;

    @OneToMany(mappedBy = "commenter")
    private List<Comment> comments;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        grantedAuthorityList.add(new SimpleGrantedAuthority(role.toString()));

        return grantedAuthorityList;
    }

    public boolean hasFavourited(Animal animal) {
        for (UserAnimalFavourites like : likes) {
            if(like.getAnimal().getAnimalId() == animal.getAnimalId()){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
