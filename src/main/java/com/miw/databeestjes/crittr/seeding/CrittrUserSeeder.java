package com.miw.databeestjes.crittr.seeding;

import com.miw.databeestjes.crittr.model.UserRoleStatus;
import com.miw.databeestjes.crittr.service.implementation.CrittrUserDetailsService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CrittrUserSeeder {
    private CrittrUserDetailsService crittrUserDetailsService;

    public CrittrUserSeeder(CrittrUserDetailsService crittrUserDetailsService) {
        this.crittrUserDetailsService = crittrUserDetailsService;
    }

    @EventListener
    public void seedCrittrUser(ContextRefreshedEvent event) {
        if (crittrUserDetailsService.findAll().isEmpty()) {
            crittrUserDetailsService.addCrittrUser("h.smith@crittr.com", "Harry Smith", "TemporaryPassword123", UserRoleStatus.ROLE_ADMIN);
            crittrUserDetailsService.addCrittrUser("c.baker@crittr.com", "Caroline Baker", "TemporaryPassword123", UserRoleStatus.ROLE_CARETAKER);
            crittrUserDetailsService.addCrittrUser("l.brown@crittr.com", "Laura Brown", generateCommonLangPassword(), UserRoleStatus.ROLE_CARETAKER);
            crittrUserDetailsService.addCrittrUser("o.davies@crittr.com", "Oliver Davies", generateCommonLangPassword(), UserRoleStatus.ROLE_CARETAKER);
            crittrUserDetailsService.addCrittrUser("t.murphy@crittr.com", "Tracy Murphy", generateCommonLangPassword(), UserRoleStatus.ROLE_CARETAKER);
            crittrUserDetailsService.addCrittrUser("e.jones@member.com", "Emily Jones", generateCommonLangPassword(), UserRoleStatus.ROLE_MEMBER);
            crittrUserDetailsService.addCrittrUser("j.obrien@member.com", "James O'Brien", generateCommonLangPassword(), UserRoleStatus.ROLE_MEMBER);
            crittrUserDetailsService.addCrittrUser("w.wilson@member.com", "William Wilson", generateCommonLangPassword(), UserRoleStatus.ROLE_MEMBER);
            crittrUserDetailsService.addCrittrUser("l.evans@member.com", "Lily Evans", generateCommonLangPassword(), UserRoleStatus.ROLE_MEMBER);
            crittrUserDetailsService.addCrittrUser("j.roberts@member.com", "Jessica Roberts", generateCommonLangPassword(), UserRoleStatus.ROLE_MEMBER);
        }
    }

    public String generateCommonLangPassword() {
        String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
        String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
        String numbers = RandomStringUtils.randomNumeric(2);
        String specialChar = RandomStringUtils.random(2, 33, 47, false, false);
        String totalChars = RandomStringUtils.randomAlphanumeric(2);
        String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
                .concat(numbers)
                .concat(specialChar)
                .concat(totalChars);
        List<Character> pwdChars = combinedChars.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(pwdChars);
        String password = pwdChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return password;
    }
}
