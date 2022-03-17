package com.miw.databeestjes.crittr.seeding;

import com.miw.databeestjes.crittr.model.UserRoleStatus;
import com.miw.databeestjes.crittr.service.implementation.CrittrUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author Annemiek Blaauwgeers <a.blaauwgeers@st.hanze.nl>
 * This class seeds the database with Crittrusers
 */

@Component
public class CrittrUserSeeder {
    private CrittrUserDetailsService crittrUserDetailsService;

    public CrittrUserSeeder(CrittrUserDetailsService crittrUserDetailsService) {
        this.crittrUserDetailsService = crittrUserDetailsService;
    }

    public void seedCrittrUser() {
        if (crittrUserDetailsService.findAll().isEmpty()) {
            crittrUserDetailsService.addCrittrUser("h.smith@crittr.com", "Harry Smith", "test", UserRoleStatus.ROLE_ADMIN);
            crittrUserDetailsService.addCrittrUser("c.baker@crittr.com", "Caroline Baker", "test", UserRoleStatus.ROLE_CARETAKER);
            crittrUserDetailsService.addCrittrUser("l.brown@crittr.com", "Laura Brown", "TemporaryPassword123", UserRoleStatus.ROLE_CARETAKER);
            crittrUserDetailsService.addCrittrUser("o.davies@crittr.com", "Oliver Davies", "TemporaryPassword123", UserRoleStatus.ROLE_CARETAKER);
            crittrUserDetailsService.addCrittrUser("t.murphy@crittr.com", "Tracy Murphy", "TemporaryPassword123", UserRoleStatus.ROLE_CARETAKER);
            crittrUserDetailsService.addCrittrUser("e.jones@member.com", "Emily Jones", "test", UserRoleStatus.ROLE_MEMBER);
            crittrUserDetailsService.addCrittrUser("j.obrien@member.com", "James O'Brien", "TemporaryPassword123", UserRoleStatus.ROLE_MEMBER);
            crittrUserDetailsService.addCrittrUser("w.wilson@member.com", "William Wilson", "TemporaryPassword123", UserRoleStatus.ROLE_MEMBER);
            crittrUserDetailsService.addCrittrUser("j.roberts@member.com", "Jessica Roberts", "TemporaryPassword123", UserRoleStatus.ROLE_MEMBER);
            crittrUserDetailsService.addCrittrUser("l.evans@member.com", "Lily Evans", "TemporaryPassword123", UserRoleStatus.ROLE_MEMBER);
        }
    }
}
