package com.kamiroo.todomanager.rest;

import com.kamiroo.todomanager.repo.CommentEntity;
import com.kamiroo.todomanager.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentRestController extends AbstractToDoRestController{

    @Autowired
    private CommentService commentService;

    @GetMapping("/getAllComments")
    public List<CommentEntity> getAllComments() {
        return commentService.getAllComments();
    }

}
