package com.miw.databeestjes.crittr.testing.unittesting;

import com.miw.databeestjes.crittr.model.Comment;
import com.miw.databeestjes.crittr.repository.CommentRepository;
import com.miw.databeestjes.crittr.service.implementation.CommentServiceImplementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CommentServiceImplementationTest {

    @Mock
    CommentRepository commentRepository;

    CommentServiceImplementation testCommentServiceImp;

    private Comment testComment;

    @BeforeEach
    public void setup(){
        testCommentServiceImp = new CommentServiceImplementation(commentRepository);
        testComment = new Comment();
        testComment.setCommentId(-1);
    }

    @Test
    public void testSave() {
        doAnswer(invocation -> {
            Comment comment = invocation.getArgument(0);

            assertEquals(testComment, comment, "Failed to save comment");
            return null;
        }).when(commentRepository).save(any(Comment.class));

        testCommentServiceImp.save(testComment);
    }

    @Test
    public void testGetAll() {
        when(testCommentServiceImp.getAll()).thenReturn(new ArrayList<>());
        List<Comment> commentsFound = testCommentServiceImp.getAll();
        assertNotNull(commentsFound, "No comments found");
    }
}