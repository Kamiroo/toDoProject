package com.kamiroo.todomanager.rest;

import com.kamiroo.todomanager.PriorityEnum;
import com.kamiroo.todomanager.StatusEnum;
import com.kamiroo.todomanager.ToDo;
import com.kamiroo.todomanager.repo.ToDoEntity;
import com.kamiroo.todomanager.repo.UserEntity;
import com.kamiroo.todomanager.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;
import javax.validation.Valid;
import java.util.List;
import java.util.Random;

@RestController
public class ToDoRestController extends AbstractToDoRestController{

    public Random randomNumber = new Random();

    @Autowired
    private ToDoService toDoService;

    @PostMapping("/addTodo")
    public ToDoEntity addTodo(@Valid  @RequestBody ToDo toDo) {
        return toDoService.addTodo(toDo);
    }

    @GetMapping("/findTodoForUser")
    public List<ToDoEntity> findTodoForUser(Long userId) {
        return toDoService.getTodoForUserWithId(userId);
    }

    @GetMapping("/getTodoByTodoId")
    public List<ToDoEntity> getTodoByTodoId(@RequestParam(defaultValue = "5") Long todoId) {
        return toDoService.getTodoByTodoId(todoId);
    }

    @GetMapping("/getTodoForUserWhereStatus")
    public List<ToDoEntity> getTodoForUserWhereStatus(Long userId, StatusEnum statusEnum) {
        return toDoService.getToDoEntityByToDoIdAndStatus(userId, statusEnum);
    }

    @GetMapping("/getTodoForUserWhereStatusAndPriority")
    public List<ToDoEntity> getTodoForUserWhereStatusAndPriority(Long userId, StatusEnum statusEnum, PriorityEnum priorityEnum) {
        return toDoService.getTodoForUserWhereStatusAndPriority(userId, statusEnum, priorityEnum);
    }

    @DeleteMapping("/deleteTodoByTodoId")
    public void deleteTodoByTodoId(Long todoId) {
        toDoService.deleteByToDoIdWhereStatusIsOpen(todoId);
    }

    @PutMapping("/updateTodoOnTitleAndDescription")
    public void updateTodoOnTitleAndDescription(Long todoId, String title, String description) {
        toDoService.updateTodoOnTitleAndDescription(todoId, title, description);
    }

    @PutMapping("/updateTodoOnPriority")
    public void updateTodoOnPriority(Long todoId, PriorityEnum priorityEnum) {
        toDoService.updateTodoOnPriority(todoId, priorityEnum);
    }
}
