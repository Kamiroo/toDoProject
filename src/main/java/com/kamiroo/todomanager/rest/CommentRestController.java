package com.kamiroo.todomanager.rest;

import com.kamiroo.todomanager.Comment;
import com.kamiroo.todomanager.repo.CommentEntity;
import com.kamiroo.todomanager.service.CommentService;
import com.kamiroo.todomanager.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentRestController extends AbstractToDoRestController{

    @Autowired
    private CommentService commentService;

    @Autowired
    private ToDoService toDoService;

    @GetMapping("/getAllComments")
    public List<CommentEntity> getAllComments() {
        return commentService.getAllComments();
    }

}
