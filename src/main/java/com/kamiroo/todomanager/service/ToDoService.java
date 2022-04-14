package com.kamiroo.todomanager.service;

import com.kamiroo.todomanager.ToDo;
import com.kamiroo.todomanager.repo.ToDoEntity;
import com.kamiroo.todomanager.repo.ToDoRepository;
import com.kamiroo.todomanager.repo.UserEntity;
import com.kamiroo.todomanager.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private UserService userService;

    public boolean addTodo(ToDo toDo) {
        UserEntity user = userService.findUserById(toDo.getUserId());
        ToDoEntity toDoEntity = new ToDoEntity();
        toDoEntity.setTitle(toDo.getTitle());
        toDoEntity.setDescription(toDo.getDescription());
        toDoEntity.setPriority(toDo.getPriority());
        toDoEntity.setStatus(toDo.getStatus());
//        toDoRepository.save(toDoEntity);
        toDoEntity = toDoRepository.save(toDoEntity);
        user.getToDoEntities().add(toDoEntity);
        userService.saveUser(user);
        return true;
    }

}
