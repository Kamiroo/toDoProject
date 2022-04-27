package com.kamiroo.todomanager.service;

import com.kamiroo.todomanager.Comment;
import com.kamiroo.todomanager.exception.ResourceNotFoundException;
import com.kamiroo.todomanager.repo.CommentEntity;
import com.kamiroo.todomanager.repo.CommentRepository;
import com.kamiroo.todomanager.repo.ToDoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ToDoService toDoService;

    public CommentEntity findById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("User with id " + commentId + " not found"));
    }

    public List<CommentEntity> getAllComments() {
        return commentRepository.findAll();
    }

}
