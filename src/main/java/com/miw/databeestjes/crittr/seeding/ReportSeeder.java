package com.miw.databeestjes.crittr.seeding;

import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.model.ReportPriority;
import com.miw.databeestjes.crittr.model.ReportStatus;
import com.miw.databeestjes.crittr.service.AnimalService;
import com.miw.databeestjes.crittr.service.ReportService;
import com.miw.databeestjes.crittr.service.implementation.CrittrUserDetailsService;
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
    private AnimalService animalService;

    public ReportSeeder(ReportService reportService, CrittrUserDetailsService crittrUserDetailsService, AnimalService animalService) {
        this.reportService = reportService;
        this.crittrUserDetailsService = crittrUserDetailsService;
        this.animalService = animalService;
    }

    public void seedReport(){
        List<CrittrUser> allUsers = crittrUserDetailsService.findAll();
        if(reportService.getAll().isEmpty()) {
            reportService.addNew("Wounded", animalService.findByAnimalId(1).get(), "Gary", "Goat", "In the shoulder", allUsers.get(5), ReportStatus.NEW ,ReportPriority.MEDIUM);
            reportService.addNew("Limps", null, "", "Deer", "", allUsers.get(9), ReportStatus.CLOSED);
            reportService.addNew("Bit me", animalService.findByAnimalId(11).get(), "Danny", "Donkey", "I was trying to feed it and it bit me", allUsers.get(7), ReportStatus.DISCARDED);
            reportService.addNew("Bit of a limp", null, "", "Sheep", "", allUsers.get(6), ReportStatus.CLOSED);
            reportService.addNew("Bleeding a little", null, "", "Goat", "", allUsers.get(9), ReportStatus.UNDER_OBSERVATION);
            reportService.addNew("Appears unhealthy", animalService.findByAnimalId(4).get(), "Grace", "Goat", "Weeping, probably an eye infection", allUsers.get(8), ReportStatus.UNDER_OBSERVATION);
            reportService.addNew("Sick", null, "", "Goat", "", allUsers.get(9), ReportStatus.DISCARDED);
            reportService.addNew("Not walking well", null, "", "Sheep", "We saw that a sheep had some difficulty walking this Wednesday", allUsers.get(5), ReportStatus.UNDER_OBSERVATION);
            reportService.addNew("Myxomatosis", null, "", "Rabit", "I'm a vet, and I recommend immediate action", allUsers.get(5), ReportStatus.UNDER_OBSERVATION, ReportPriority.HIGH);
            reportService.addNew("Looks really sick", null, "", "Rabbit", "", allUsers.get(7), ReportStatus.DISCARDED, ReportPriority.HIGH);
            reportService.addNew("Diseased", null, "", "Rabbit", "Mixomitosis or something, I think?", allUsers.get(9), ReportStatus.DISCARDED);
            reportService.addNew("deformed", animalService.findByAnimalId(21).get(), "Rory", "Rabbit", "I think it has that one disease that makes rabbits look all terrible", allUsers.get(8), ReportStatus.CLOSED);
            reportService.addNew("Bleeding", animalService.findByAnimalId(1).get(), "Gary", "Goat", "", allUsers.get(8), ReportStatus.OPEN_ISSUE, ReportPriority.MEDIUM);
            reportService.addNew("Eye infection", null, "", "Goat", "", allUsers.get(0), ReportStatus.OPEN_ISSUE);
            reportService.addNew("Needs shaving", null, "", "Sheep", "It's still in winter fleece, you should get it shaved", allUsers.get(5), ReportStatus.DISCARDED);
            reportService.addNew("Walks with a limp", null, "", "Deer", "", allUsers.get(7), ReportStatus.CLOSED);
            reportService.addNew("Wounded", null, "", "Goat", "Just above the leg behind the head", allUsers.get(9), ReportStatus.OPEN_ISSUE, ReportPriority.MEDIUM);
            reportService.addNew("Very sick", null, "", "Rabbit", "You'll see which one I think", allUsers.get(8), ReportStatus.UNDER_OBSERVATION);
            reportService.addNew("Broken out", null, "", "Pig", "A pig was walking around outside the pen, is this supposed to happen?", allUsers.get(9), ReportStatus.DISCARDED);
            reportService.addNew("broke out", animalService.findByAnimalId(18).get(), "", "Pig", "Palpatine broke out of the fence again, just so you know", allUsers.get(5), ReportStatus.CLOSED);
        }
    }

}
