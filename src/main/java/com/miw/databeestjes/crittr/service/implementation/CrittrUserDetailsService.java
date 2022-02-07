package com.miw.databeestjes.crittr.service.implementation;

import com.miw.databeestjes.crittr.repository.CrittrUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl
 * <p>
 * Вот, что программа делает
 */

@Service
public class CrittrUserDetailsService implements UserDetailsService {

    CrittrUserRepository crittrUserRepository;

    public CrittrUserDetailsService(CrittrUserRepository crittrUserRepository) {
        this.crittrUserRepository = crittrUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return crittrUserRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " was not found."));
    }
}
