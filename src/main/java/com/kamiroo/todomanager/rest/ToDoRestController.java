package com.kamiroo.todomanager.rest;

import com.kamiroo.todomanager.ToDo;
import com.kamiroo.todomanager.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ToDoRestController extends AbstractToDoRestController{

    @Autowired
    private ToDoService toDoService;

    @PostMapping("/addTodo")
    public boolean addTodo(@Valid  @RequestBody ToDo toDo) {
        return toDoService.addTodo(toDo);
    }
}
