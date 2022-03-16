package com.miw.databeestjes.crittr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Cascade;
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

    @OneToMany(mappedBy = "reporter", cascade = CascadeType.ALL)
    private List<Report> reports;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserAnimalFavourites> likes;

    @OneToMany(mappedBy = "claimer", cascade = CascadeType.ALL)
    private List<Report> claimedReports;

    @OneToMany(mappedBy = "commenter", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        grantedAuthorityList.add(new SimpleGrantedAuthority(role.toString()));

        return grantedAuthorityList;
    }

    public boolean equals(CrittrUser otherUser) {
        return this.userId == otherUser.getUserId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
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
