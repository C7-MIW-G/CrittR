package com.miw.databeestjes.crittr.service.implementation;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.model.Comment;
import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.repository.CommentRepository;
import com.miw.databeestjes.crittr.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ivo Didden <i.l.didden@st.hanze.nl>
 * <p>
 * This file is part of our Crittr Project
 */

@Service
public class CommentServiceImplementation implements CommentService {

    CommentRepository commentRepository;

    public CommentServiceImplementation(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> getAllByAnimalId(long animalId) {
        return commentRepository.findAllByAnimalId(animalId);
    }

    @Override
    public void addComment(CrittrUser commenter, Animal animal, String commentText) {
        Comment comment = new Comment();
        comment.setCommenter(commenter);
        comment.setAnimal(animal);
        comment.setCommentText(commentText);
        commentRepository.save(comment);
    }
}
