package com.miw.databeestjes.crittr.service;

import com.miw.databeestjes.crittr.model.Animal;
import com.miw.databeestjes.crittr.model.Comment;
import com.miw.databeestjes.crittr.model.CrittrUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    void save (Comment comment);

    List<Comment> getAll();

    List<Comment> getAllByAnimalId(long animalId);

    void addComment(CrittrUser commenter, Animal animal, String commentText);
}
