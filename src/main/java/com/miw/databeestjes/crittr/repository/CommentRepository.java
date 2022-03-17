package com.miw.databeestjes.crittr.repository;

import com.miw.databeestjes.crittr.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository <Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.animal.animalId=?1")
    List<Comment> findAllByAnimalId (long animalId);

}
