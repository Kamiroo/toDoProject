package com.kamiroo.todomanager.rest;

import com.kamiroo.todomanager.Comment;
import com.kamiroo.todomanager.PriorityEnum;
import com.kamiroo.todomanager.StatusEnum;
import com.kamiroo.todomanager.ToDo;
import com.kamiroo.todomanager.repo.CommentEntity;
import com.kamiroo.todomanager.repo.ToDoEntity;
import com.kamiroo.todomanager.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
public class ToDoRestController extends AbstractToDoRestController{

    @Autowired
    private ToDoService toDoService;

    @PostMapping("/addTodo")
    public ToDoEntity addTodo(@Valid @RequestBody ToDo toDo) {
        return toDoService.addTodo(toDo);
    }

    @GetMapping("/findTodoForUser")
    public List<ToDoEntity> findTodoForUser(@RequestParam Long userId) {
        return toDoService.getTodoForUserWithId(userId);
    }

    @GetMapping("/getTodoByTodoId")
    public ToDoEntity getTodoByTodoId(@RequestParam(defaultValue = "5") Long todoId) {
        return toDoService.getTodoByTodoId(todoId);
    }

    @GetMapping("/getTodoForUserWhereStatus")
    public List<ToDoEntity> getTodoForUserWhereStatus(@RequestParam Long userId, @RequestParam StatusEnum statusEnum) {
        return toDoService.getToDoEntityByToDoIdAndStatus(userId, statusEnum);
    }

    @GetMapping("/getToDoEntityByToDoIdAndStatusJava")
    public List<ToDoEntity> getToDoEntityByToDoIdAndStatusJava(@RequestParam Long userId, @RequestParam StatusEnum statusEnum) {
        return toDoService.getToDoEntityByToDoIdAndStatusJava(userId, statusEnum);
    }

    @GetMapping("/getTodoForUserWhereStatusAndPriority")
    public List<ToDoEntity> getTodoForUserWhereStatusAndPriority(@RequestParam Long userId, @RequestParam StatusEnum statusEnum, @RequestParam PriorityEnum priorityEnum) {
        return toDoService.getTodoForUserWhereStatusAndPriority(userId, statusEnum, priorityEnum);
    }

    @GetMapping("/getTodoForUserWhereStatusAndPriorityJava")
    public List<ToDoEntity> getTodoForUserWhereStatusAndPriorityJava(@RequestParam Long userId, @RequestParam StatusEnum statusEnum, @RequestParam PriorityEnum priorityEnum) {
        return toDoService.getTodoForUserWhereStatusAndPriorityJava(userId, statusEnum, priorityEnum);
    }

    @DeleteMapping("/deleteTodoByTodoId")
    public void deleteTodoByTodoId(@RequestParam Long todoId) {
        toDoService.deleteByToDoIdWhereStatusIsOpen(todoId);
    }

    @PutMapping("/updateTodoOnTitleAndDescription")
    public void updateTodoOnTitleAndDescription(@RequestParam Long todoId, @RequestParam String title, @RequestParam String description) {
        toDoService.updateTodoOnTitleAndDescription(todoId, title, description);
    }

    @PutMapping("/updateTodoOnPriority")
    public void updateTodoOnPriority(@RequestParam Long todoId, @RequestParam PriorityEnum priorityEnum) {
        toDoService.updateTodoOnPriority(todoId, priorityEnum);
    }

    @PutMapping("/updateTodoOnStatus")
    public void updateTodoOnStatus(@RequestParam Long todoId, @RequestParam StatusEnum statusEnum) {
        toDoService.updateTodoOnStatus(todoId, statusEnum);
    }

    @PostMapping("/updateStatusAndOptionalAddComment")
    public void updateStatusAndOptionalAddComment(@RequestParam Long todoId, @RequestParam StatusEnum statusEnum, @RequestBody(required = false) Comment comment ) {
        toDoService.updateStatusAndOptionalAddComment(todoId, statusEnum, Optional.ofNullable(comment));
    }

    @PostMapping("/addComment")
    public CommentEntity addComment(@RequestBody Comment comment) {
        return toDoService.addComment(comment);
    }

    @GetMapping("/getAllCommentsForTodoId")
    public List<CommentEntity> getAllCommentsForTodoId(@RequestParam Long todoId) {
        return toDoService.getAllCommentsForTodoId(todoId);
    }

}
