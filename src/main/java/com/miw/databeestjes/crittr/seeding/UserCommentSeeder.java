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

    public UserCommentSeeder(CommentService commentService, CrittrUserDetailsService crittrUserDetailsService, AnimalService animalService) {
        this.commentService = commentService;
        this.crittrUserDetailsService = crittrUserDetailsService;
        this.animalService = animalService;
    }

    private CrittrUser getRandomUser(List<CrittrUser> listOfUsers) {
        return listOfUsers.get((int) (Math.random() * NUMBER_OF_FULL_NAME_USERS));
    }

    private Animal getRandomAnimal() {
        return animalService.getAll().get((int) (Math.random() * animalService.getAll().size()));
    }

    @EventListener
    public void seedComments(ContextRefreshedEvent event) {
        List<CrittrUser> allUsers = crittrUserDetailsService.findAll();
        if (commentService.getAll().size() == 0) {
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "So cuuuute!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Was so sweet to me today");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Really loves pets!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Always comes running whenever I visit! :)");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Adorable!!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Always makes my day!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Was not in the mood for pets today :(");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "My favorite!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Awww my absolute favourite!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Look out! Can be mean when grumpy... Might be my fault though :')");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "I want to take it hooooome");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Wish it could be my pet! So cute!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Such a cutey, just the sweetest!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "This guy stinks!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "This guy is the GOAT of the farm, get it?? Hahahaha! I'll see myself out.");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "So great with the kids. The kids are in love, want to visit everyday. :)");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Love it!!!!!!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "The best of the entire petting zoo!!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Such a charmer <3");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "The reason I come here everyday. Always so sweet :)");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Careful, does NOT want to cuddled sometimes");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Loves being cuddled. Perfect!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Seemed really happy today!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Was super energetic today!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Was being a bully to the animals today, very mean!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Seems to really enjoy the company of visitors! Fun :)");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Grumpy today, watch out!!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Looking so happy and healthy. I think they're having a great life here");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "This guy is so mean to me. I like the other animals way better...");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "CUTECUTECUTECUTE!!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Cuteness OVERLOAD!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Haha was so loud today! Making noise all the time! adorable");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Was playing with the ball all day, so energetic!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "I wanna take it home <3<3<3<3");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Yeeesssssssssss");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "My Favvvvvv.");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "The best!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Loves to play with the other animals, so fun to see. Seems so happy here.");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "I feel their shelter should be bigger! More space is better!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Their pen could use a good clean. Pick up a broom and get to it guys!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "So playful :))");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "<3<3<3");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Cuddle all day!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "So handsome!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Beautiful creature!!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "I want one at home!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "THE BEST");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Love love love it!");
            commentService.addComment(getRandomUser(allUsers), getRandomAnimal(), "Cute, but smells really bad!");

        }
    }
}