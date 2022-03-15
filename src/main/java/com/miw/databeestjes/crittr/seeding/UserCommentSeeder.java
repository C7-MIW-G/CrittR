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

    public void seedComments() {
        if (commentService.getAll().size() == 0) {
            //Size of listOfUsers with proper names is fixed at 10
            List<CrittrUser> listOfUsers = crittrUserDetailsService.findAll();
            //Size of listOfAnimals is fixed at 25
            List<Animal> listOfAnimals = animalService.getAll();
            commentService.addComment(listOfUsers.get(0), listOfAnimals.get(0), "So cuuuute!");
            commentService.addComment(listOfUsers.get(1), listOfAnimals.get(0), "Was so sweet to me today");
            commentService.addComment(listOfUsers.get(2), listOfAnimals.get(0), "Really loves pets!");
            commentService.addComment(listOfUsers.get(3), listOfAnimals.get(1), "Always comes running whenever I visit! :)");
            commentService.addComment(listOfUsers.get(4), listOfAnimals.get(1), "Adorable!!");
            commentService.addComment(listOfUsers.get(5), listOfAnimals.get(2), "Always makes my day!");
            commentService.addComment(listOfUsers.get(6), listOfAnimals.get(2), "Was not in the mood for pets today :(");
            commentService.addComment(listOfUsers.get(7), listOfAnimals.get(3), "My favorite!");
            commentService.addComment(listOfUsers.get(8), listOfAnimals.get(3), "Awww my absolute favourite!");
            commentService.addComment(listOfUsers.get(9), listOfAnimals.get(3), "Look out! Can be mean when grumpy... Might be my fault though :')");
            commentService.addComment(listOfUsers.get(0), listOfAnimals.get(4), "I want to take it hooooome");
            commentService.addComment(listOfUsers.get(1), listOfAnimals.get(4), "Wish it could be my pet! So cute!");
            commentService.addComment(listOfUsers.get(2), listOfAnimals.get(4), "Such a cutey, just the sweetest!");
            commentService.addComment(listOfUsers.get(3), listOfAnimals.get(5), "This guy stinks!");
            commentService.addComment(listOfUsers.get(4), listOfAnimals.get(5), "This guy is the GOAT of the farm, get it?? Hahahaha! I'll see myself out.");
            commentService.addComment(listOfUsers.get(5), listOfAnimals.get(5), "So great with the kids. The kids are in love, want to visit everyday. :)");
            commentService.addComment(listOfUsers.get(6), listOfAnimals.get(6), "Love it!!!!!!");
            commentService.addComment(listOfUsers.get(7), listOfAnimals.get(6), "The best of the entire petting zoo!!");
            commentService.addComment(listOfUsers.get(8), listOfAnimals.get(7), "Such a charmer <3");
            commentService.addComment(listOfUsers.get(9), listOfAnimals.get(7), "The reason I come here everyday. Always so sweet :)");
            commentService.addComment(listOfUsers.get(0), listOfAnimals.get(8), "Careful, does NOT want to cuddled sometimes");
            commentService.addComment(listOfUsers.get(1), listOfAnimals.get(8), "Loves being cuddled. Perfect!");
            commentService.addComment(listOfUsers.get(2), listOfAnimals.get(9), "Seemed really happy today!");
            commentService.addComment(listOfUsers.get(2), listOfAnimals.get(9), "Was super energetic today!");
            commentService.addComment(listOfUsers.get(3), listOfAnimals.get(10), "Was being a bully to the animals today, very mean!");
            commentService.addComment(listOfUsers.get(4), listOfAnimals.get(10), "Seems to really enjoy the company of visitors! Fun :)");
            commentService.addComment(listOfUsers.get(5), listOfAnimals.get(10), "Grumpy today, watch out!!");
            commentService.addComment(listOfUsers.get(6), listOfAnimals.get(12), "Looking so happy and healthy. I think they're having a great life here");
            commentService.addComment(listOfUsers.get(7), listOfAnimals.get(13), "This guy is so mean to me. I like the other animals way better...");
            commentService.addComment(listOfUsers.get(8), listOfAnimals.get(13), "CUTECUTECUTECUTE!!");
            commentService.addComment(listOfUsers.get(9), listOfAnimals.get(14), "Cuteness OVERLOAD!");
            commentService.addComment(listOfUsers.get(0), listOfAnimals.get(15), "Haha was so loud today! Making noise all the time! adorable");
            commentService.addComment(listOfUsers.get(1), listOfAnimals.get(16), "Was playing with the ball all day, so energetic!");
            commentService.addComment(listOfUsers.get(2), listOfAnimals.get(17), "I wanna take it home <3<3<3<3");
            commentService.addComment(listOfUsers.get(3), listOfAnimals.get(18), "Yeeesssssssssss");
            commentService.addComment(listOfUsers.get(4), listOfAnimals.get(19), "My Favvvvvv.");
            commentService.addComment(listOfUsers.get(5), listOfAnimals.get(20), "The best!");
            commentService.addComment(listOfUsers.get(6), listOfAnimals.get(21), "Loves to play with the other animals, so fun to see. Seems so happy here.");
            commentService.addComment(listOfUsers.get(7), listOfAnimals.get(22), "I feel their shelter should be bigger! More space is better!");
            commentService.addComment(listOfUsers.get(8), listOfAnimals.get(23), "Their pen could use a good clean. Pick up a broom and get to it guys!");
            commentService.addComment(listOfUsers.get(9), listOfAnimals.get(24), "So playful :))");
            commentService.addComment(listOfUsers.get(0), listOfAnimals.get(24), "<3<3<3");
            commentService.addComment(listOfUsers.get(1), listOfAnimals.get(7), "Cuddle all day!");
            commentService.addComment(listOfUsers.get(2), listOfAnimals.get(21), "So handsome!");
            commentService.addComment(listOfUsers.get(3), listOfAnimals.get(17), "Beautiful creature!!");
            commentService.addComment(listOfUsers.get(4), listOfAnimals.get(15), "I want one at home!");
            commentService.addComment(listOfUsers.get(5), listOfAnimals.get(16), "THE BEST");
            commentService.addComment(listOfUsers.get(6), listOfAnimals.get(18), "Love love love it!");
            commentService.addComment(listOfUsers.get(7), listOfAnimals.get(2), "Cute, but smells really bad!");

        }
    }
}