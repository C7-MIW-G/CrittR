package com.miw.databeestjes.crittr.service.implementation;

import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.repository.CrittrUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void delete(long userId) {
        crittrUserRepository.deleteByUserId(userId);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return crittrUserRepository.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("User with name " + email + " was not found."));
    }

    public String saveWithPassword(CrittrUser user, String passwordHash) {
        user.setPassword(passwordHash);
        crittrUserRepository.save(user);
        return "redirect:/user/details/" + user.getUserId();
    }

    public String saveWithoutPassword(CrittrUser user, CrittrUser currentUser) {
        user.setPassword(currentUser.getPassword());
        crittrUserRepository.save(user);
        return "redirect:/user/details/" + user.getUserId();
    }

    public void save(CrittrUser user) {
        crittrUserRepository.save(user);
    }
}
