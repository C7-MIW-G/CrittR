package com.miw.databeestjes.crittr.seeding;

import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.service.ReportService;
import com.miw.databeestjes.crittr.service.implementation.CrittrUserDetailsService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl
 * <p>
 * This class seeds the database with some reports
 */

@Component
public class ReportSeeder {

    private ReportService reportService;
    private CrittrUserDetailsService crittrUserDetailsService;

    public ReportSeeder(ReportService reportService, CrittrUserDetailsService crittrUserDetailsService) {
        this.reportService = reportService;
        this.crittrUserDetailsService = crittrUserDetailsService;
    }

    @EventListener
    public void seedReport(ContextRefreshedEvent event){
        List<CrittrUser> allUsers = crittrUserDetailsService.findAll();
        if(reportService.getAll().isEmpty()) {
            reportService.addNew("Wounded", "Gary", "Goat", "In the shoulder", allUsers.get(5));
            reportService.addNew("Limps", "", "Deer", "", allUsers.get(9));
            reportService.addNew("Bit me", "Danny", "Donkey", "I was trying to feed it and it bit me", allUsers.get(7));
            reportService.addNew("Bit of a limp", "", "Sheep", "", allUsers.get(6));
            reportService.addNew("Bleeding a little", "", "Goat", "", allUsers.get(9));
            reportService.addNew("Appears unhealthy", "Grace", "Goat", "Weeping, probably an eye infection", allUsers.get(8));
            reportService.addNew("Sick", "", "Goat", "", allUsers.get(9));
            reportService.addNew("Not walking well", "", "Sheep", "We saw that a sheep had some difficulty walking this Wednesday", allUsers.get(5));
            reportService.addNew("Myxomatosis", "", "Rabit", "I'm a vet, and I recommend immediate action", allUsers.get(5));
            reportService.addNew("Looks really sick", "", "Rabbit", "", allUsers.get(7));
            reportService.addNew("Diseased", "", "Rabbit", "Mixomitosis or something, I think?", allUsers.get(9));
            reportService.addNew("deformed", "Rory", "Rabbit", "I think it has that one disease that makes rabbits look all terrible", allUsers.get(8));
            reportService.addNew("Bleeding", "Gary", "Goat", "", allUsers.get(8));
            reportService.addNew("Eye infection", "", "Goat", "", allUsers.get(0));
            reportService.addNew("Needs shaving", "", "Sheep", "It's still in winter fleece, you should get it shaved", allUsers.get(5));
            reportService.addNew("Walks with a limp", "", "Deer", "", allUsers.get(7));
            reportService.addNew("Wounded", "", "Goat", "Just above the leg behind the head", allUsers.get(9));
            reportService.addNew("Very sick", "", "Rabbit", "You'll see which one I think", allUsers.get(8));
            reportService.addNew("Broken out", "", "Pig", "A pig was walking around outside the pen, is this supposed to happen?", allUsers.get(9));
            reportService.addNew("broke out", "Palpatine", "Pig", "Palpatine broke out of the fence again, just so you know", allUsers.get(5));
        }
    }

}
