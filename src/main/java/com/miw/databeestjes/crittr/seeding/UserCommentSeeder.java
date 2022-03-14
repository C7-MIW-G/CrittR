package com.miw.databeestjes.crittr.seeding;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.service.AnimalService;
import com.miw.databeestjes.crittr.service.CommentService;
import com.miw.databeestjes.crittr.service.implementation.CrittrUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author Ivo Didden <i.l.didden@st.hanze.nl>
 * <p>
 * This file is used to seed comments of random users to random animals in our database
 */
@Component
public class UserCommentSeeder {

    private static final int NUMBER_OF_FULL_NAME_USERS = 10;
    private CommentService commentService;
    private CrittrUserDetailsService crittrUserDetailsService;
    private AnimalService animalService;
    private List<CrittrUser> allUsers;
    private List<Animal> allAnimals;

    public UserCommentSeeder(CommentService commentService, CrittrUserDetailsService crittrUserDetailsService, AnimalService animalService) {
        this.commentService = commentService;
        this.crittrUserDetailsService = crittrUserDetailsService;
        this.animalService = animalService;
        this.allUsers = crittrUserDetailsService.findAll();
        this.allAnimals = animalService.getAll();
    }

    private CrittrUser getRandomUser() {
        return allUsers.get((int) (Math.random() * NUMBER_OF_FULL_NAME_USERS));
    }

    private Animal getRandomAnimal() {
        return allAnimals.get((int) (Math.random() * allAnimals.size()));
    }

    @EventListener
    public void seedComments(ContextRefreshedEvent event) {
        List<CrittrUser> allUsers = crittrUserDetailsService.findAll();
        if (commentService.getAll().size() == 0) {
            commentService.addComment(getRandomUser(), getRandomAnimal(), "So cuuuute!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Was so sweet to me today");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Really loves pets!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Always comes running whenever I visit! :)");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Adorable!!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Always makes my day!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Was not in the mood for pets today :(");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "My favorite!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Awww my absolute favourite!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Look out! Can be mean when grumpy... Might be my fault though :')");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "I want to take it hooooome");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Wish it could be my pet! So cute!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Such a cutey, just the sweetest!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "This guy stinks!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "This guy is the GOAT of the farm, get it?? Hahahaha! I'll see myself out.");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "So great with the kids. The kids are in love, want to visit everyday. :)");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Love it!!!!!!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "The best of the entire petting zoo!!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Such a charmer <3");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "The reason I come here everyday. Always so sweet :)");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Careful, does NOT want to cuddled sometimes");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Loves being cuddled. Perfect!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Seemed really happy today!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Was super energetic today!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Was being a bully to the animals today, very mean!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Seems to really enjoy the company of visitors! Fun :)");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Grumpy today, watch out!!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Looking so happy and healthy. I think they're having a great life here");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "This guy is so mean to me. I like the other animals way better...");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "CUTECUTECUTECUTE!!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Cuteness OVERLOAD!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Haha was so loud today! Making noise all the time! adorable");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Was playing with the ball all day, so energetic!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "I wanna take it home <3<3<3<3");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Yeeesssssssssss");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "My Favvvvvv.");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "The best!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Loves to play with the other animals, so fun to see. Seems so happy here.");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "I feel their shelter should be bigger! More space is better!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Their pen could use a good clean. Pick up a broom and get to it guys!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "So playful :))");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "<3<3<3");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Cuddle all day!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "So handsome!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Beautiful creature!!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "I want one at home!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "THE BEST");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Love love love it!");
            commentService.addComment(getRandomUser(), getRandomAnimal(), "Cute, but smells really bad!");

        }
    }
}