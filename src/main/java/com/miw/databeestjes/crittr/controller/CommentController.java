package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.model.Comment;
import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.service.AnimalService;
import com.miw.databeestjes.crittr.service.CommentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Ivo Didden <i.l.didden@st.hanze.nl>
 * <p>
 * This file controls the comments section of the animal detail page
 */
@Controller
public class CommentController {

    CommentService commentService;
    AnimalService animalService;

    public CommentController(CommentService commentService, AnimalService animalService) {
        this.commentService = commentService;
        this.animalService = animalService;
    }

    @PostMapping("/comments/new")
    protected String addComment(@ModelAttribute("comment") @Valid Comment comment,
                                @AuthenticationPrincipal CrittrUser user,
                                BindingResult result,
                                long animalId){

        if(result.hasErrors()){
            return "redirect:/";
        }

        comment.setCommenter(user);
        Optional<Animal> optionalAnimal = animalService.findByAnimalId(animalId);
        comment.setAnimal(optionalAnimal.get());
        commentService.save(comment);
        return "redirect:/animals/details/" + animalId;
    }

}
