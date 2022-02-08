package com.miw.databeestjes.crittr.service.implementation;

import com.miw.databeestjes.crittr.repository.CrittrUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl
 * <p>
 * This class regulates database access for Users
 */

@Service
public class CrittrUserDetailsService implements UserDetailsService {

    CrittrUserRepository crittrUserRepository;

    public CrittrUserDetailsService(CrittrUserRepository crittrUserRepository) {
        this.crittrUserRepository = crittrUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return crittrUserRepository.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("User with name " + email + " was not found."));
    }
}
