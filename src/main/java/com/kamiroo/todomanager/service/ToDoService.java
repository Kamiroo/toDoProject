package com.kamiroo.todomanager.service;

import com.kamiroo.todomanager.PriorityEnum;
import com.kamiroo.todomanager.StatusEnum;
import com.kamiroo.todomanager.ToDo;
import com.kamiroo.todomanager.exception.ResourceNotFoundException;
import com.kamiroo.todomanager.repo.ToDoEntity;
import com.kamiroo.todomanager.repo.ToDoRepository;
import com.kamiroo.todomanager.repo.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ToDoService {

    Logger logger = LoggerFactory.getLogger(ToDoService.class);

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private UserService userService;

    @Transactional(Transactional.TxType.REQUIRED)
    public ToDoEntity addTodo(ToDo toDo) {
        UserEntity user = userService.findUserById(toDo.getUserId());
        ToDoEntity toDoEntity = new ToDoEntity();
        toDoEntity.setTitle(toDo.getTitle());
        toDoEntity.setDescription(toDo.getDescription());
        toDoEntity.setPriority(toDo.getPriority());
        toDoEntity.setStatus(toDo.getStatus());
//        toDoRepository.save(toDoEntity);
        toDoEntity = toDoRepository.save(toDoEntity);
        List<ToDoEntity> todoList;
        if(Objects.isNull(user.getToDoEntities())){
            todoList = new ArrayList<>();
        } else {
            todoList = user.getToDoEntities();
        }
        todoList.add(toDoEntity);
        user.setToDoEntities(todoList);
        userService.saveUser(user);
        logger.info("ToDo " + toDo.getTitle() + " added");
        return toDoEntity;
    }

    public List<ToDoEntity> getTodoForUserWithId(Long userId) {
        UserEntity user = userService.findUserById(userId);
        List<ToDoEntity> todoList = user.getToDoEntities();
        return todoList;
    }

    public List<ToDoEntity> getTodoByTodoId(Long todoId){
        List<ToDoEntity> list = toDoRepository.findToDoEntityByToDoId(todoId);
        if(!list.isEmpty()){
            return toDoRepository.findToDoEntityByToDoId(todoId);
        }
        else {
            throw new ResourceNotFoundException();
        }
    }

    public List<ToDoEntity> getToDoEntityByToDoIdAndStatus(Long userId, StatusEnum statusEnum) {
        return toDoRepository.findByUserIdAndStatus(userId, statusEnum.ordinal());
    }

    public List<ToDoEntity> getTodoForUserWhereStatusAndPriority(Long userId, StatusEnum statusEnum, PriorityEnum priorityEnum) {
        return toDoRepository.findByUserIdAndStatusAndPriority(userId, statusEnum.ordinal(), priorityEnum.ordinal());
    }

    public void deleteByToDoIdWhereStatusIsOpen(Long todoId) {
        List<ToDoEntity> list = toDoRepository.findToDoEntityByToDoId(todoId);
        ToDoEntity toDoEntity = list.get(0);
        if(!list.isEmpty() && toDoEntity.getStatus() == StatusEnum.OPEN) {
            toDoRepository.deleteByToDoIdWhereStatusIsOpen(todoId);
        } else if(!list.isEmpty() && toDoEntity.getStatus() != StatusEnum.OPEN) {
            throw new ResourceNotFoundException("Delete failed! You can't change todo's when it's status is IN_PROGRESS/CLOSED");
        } else {
            throw new ResourceNotFoundException();
        }

    }

    public void updateTodoOnTitleAndDescription(Long todoId, String title, String description) {
        List<ToDoEntity> list = toDoRepository.findToDoEntityByToDoId(todoId);
        if(!list.isEmpty()){
            toDoRepository.updateTodoOnTitleAndDescription(todoId, title, description);
        }
        else {
            throw new ResourceNotFoundException();
        }
    }

    public void updateTodoOnPriority(Long todoId, PriorityEnum priorityEnum) {
        List<ToDoEntity> list = toDoRepository.findToDoEntityByToDoId(todoId);
        if(!list.isEmpty()){
            toDoRepository.updateTodoOnPriority(todoId, priorityEnum.ordinal());
        }
        else {
            throw new ResourceNotFoundException();
        }
    }
}
