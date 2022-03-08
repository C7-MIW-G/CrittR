package com.miw.databeestjes.crittr.service.implementation;

import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.model.UserRoleStatus;
import com.miw.databeestjes.crittr.repository.CrittrUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public String saveWithoutPassword(CrittrUser user) {
        Optional<CrittrUser> optionalUser = crittrUserRepository.findById(user.getUserId());
        if(optionalUser.isPresent()) {
            user.setPassword(optionalUser.get().getPassword());
            crittrUserRepository.save(user);
            return "redirect:/user/details/" + user.getUserId();
        }
        return "userEditForm";
    }

    public void save(CrittrUser user) {
        crittrUserRepository.save(user);
    }

    public List<CrittrUser> listByEmail(String email) {
       return crittrUserRepository.listByEmail(email);
    }

    public Optional<CrittrUser> findById(long userId) {
        return crittrUserRepository.findById(userId);
    }

    public List<CrittrUser> findAll() {
        return crittrUserRepository.findAll();
    }

    public List<CrittrUser> searchByEmail(String email) {
        return crittrUserRepository.searchByEmail(email);
    }

    public List<CrittrUser> listByRole(UserRoleStatus role) {
        return crittrUserRepository.listByRole(role);
    }

    public void addCrittrUser(String email, String username, String password, UserRoleStatus role) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);

        CrittrUser crittrUser = new CrittrUser();
        crittrUser.setEmail(email);
        crittrUser.setUsername(username);
        crittrUser.setPassword(encodedPassword);
        crittrUser.setRole(role);

        crittrUserRepository.save(crittrUser);
    }
}
