package com.miw.databeestjes.crittr.seeding;

import com.miw.databeestjes.crittr.service.*;
import com.miw.databeestjes.crittr.service.implementation.CrittrUserDetailsService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Ivo Didden <i.l.didden@st.hanze.nl>
 * <p>
 * This file calls all the other seeders in the correct order, seeding our database with sample data.
 */

@Component
public class DatabaseSeeder {

    AnimalService animalService;
    CommentService commentService;
    CrittrUserDetailsService crittrUserDetailsService;
    EduInfoService eduInfoService;
    FunFactService funFactService;
    ReportService reportService;

    AnimalSeeder animalSeeder;
    CrittrUserSeeder crittrUserSeeder;
    EduInfoSeeder eduInfoSeeder;
    FunFactSeeder funFactSeeder;
    ReportSeeder reportSeeder;
    UserCommentSeeder userCommentSeeder;

    public DatabaseSeeder(AnimalService animalService, CommentService commentService,
                          CrittrUserDetailsService crittrUserDetailsService,
                          EduInfoService eduInfoService, FunFactService funFactService,
                          ReportService reportService) {
        this.animalService = animalService;
        this.commentService = commentService;
        this.crittrUserDetailsService = crittrUserDetailsService;
        this.eduInfoService = eduInfoService;
        this.funFactService = funFactService;
        this.reportService = reportService;
        this.animalSeeder = new AnimalSeeder(animalService);
        this.crittrUserSeeder = new CrittrUserSeeder(crittrUserDetailsService);
        this.eduInfoSeeder = new EduInfoSeeder(eduInfoService);
        this.funFactSeeder = new FunFactSeeder(funFactService);
        this.reportSeeder = new ReportSeeder(reportService, crittrUserDetailsService, animalService);
        this.userCommentSeeder = new UserCommentSeeder(commentService, crittrUserDetailsService, animalService);
    }

    @EventListener
    public void seedDatabase(ContextRefreshedEvent event){
        animalSeeder.seedAnimal();
        crittrUserSeeder.seedCrittrUser();
        eduInfoSeeder.seedEduInfo();
        funFactSeeder.seedFunFact();
        reportSeeder.seedReport();
        userCommentSeeder.seedComments();
    }

}
