package com.kamiroo.todomanager.service;

import com.kamiroo.todomanager.*;
import com.kamiroo.todomanager.exception.ResourceNotFoundException;
import com.kamiroo.todomanager.repo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ToDoService {

    Logger logger = LoggerFactory.getLogger(ToDoService.class);

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(Transactional.TxType.REQUIRED)
    public ToDoEntity addTodo(ToDo toDo) {
        UserEntity user = userService.findUserById(toDo.getUserId());
        ToDoEntity toDoEntity = new ToDoEntity();
        toDoEntity.setTitle(toDo.getTitle());
        toDoEntity.setDescription(toDo.getDescription());
        toDoEntity.setPriority(toDo.getPriority());
        toDoEntity.setStatus(toDo.getStatus());
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

    public ToDoEntity getTodoByTodoId(Long todoId){
        return toDoRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("ToDo with ID " + todoId + " not found."));
    }

    public List<ToDoEntity> getToDoEntityByToDoIdAndStatus(Long userId, StatusEnum statusEnum) {
        return toDoRepository.findByUserIdAndStatus(userId, statusEnum.ordinal());
    }

    public List<ToDoEntity> getToDoEntityByToDoIdAndStatusJava(Long userId, StatusEnum statusEnum){
        return userService.findUserById(userId)
                .getToDoEntities()
                .stream()
                .filter(status -> status.equals(statusEnum))
                .collect(Collectors.toList());
    }

    public List<ToDoEntity> getTodoForUserWhereStatusAndPriority(Long userId, StatusEnum statusEnum, PriorityEnum priorityEnum) {
        return toDoRepository.findByUserIdAndStatusAndPriority(userId, statusEnum.ordinal(), priorityEnum.ordinal());
    }

    public List<ToDoEntity> getTodoForUserWhereStatusAndPriorityJava(Long userId, StatusEnum statusEnum, PriorityEnum priorityEnum) {
        UserEntity userEntity = userService.findUserById(userId);
        List<ToDoEntity> toDoEntity = userEntity.getToDoEntities();
        List<ToDoEntity> filteredList = new ArrayList<>();
        for(ToDoEntity toDo : toDoEntity) {
            if (toDo.getStatus().equals(statusEnum) && toDo.getPriority().equals(priorityEnum)) {
                filteredList.add(toDo);
            }
        }
        return filteredList;
    }

    public void deleteByToDoIdWhereStatusIsOpen(Long todoId) {
        ToDoEntity toDoEntity = findTodoByIdOrThrowsException(todoId);

        if(toDoEntity.getStatus() == StatusEnum.OPEN) {
            toDoRepository.deleteById(todoId);
        } else {
            throw new ResourceNotFoundException("Delete failed! You can't change todo's when it's status is IN_PROGRESS/CLOSED");
        }
    }

    public void updateTodoOnTitleAndDescription(Long todoId, String title, String description) {
        toDoRepository.updateTodoOnTitleAndDescription(todoId, title, description);
    }

    public void updateTodoOnPriority(Long todoId, PriorityEnum priorityEnum) {
        toDoRepository.updateTodoOnPriority(todoId, priorityEnum.ordinal());
    }

    public ToDoEntity updateTodoOnStatus(Long todoId, StatusEnum statusEnum) {
        ToDoEntity toDoEntity = findTodoByIdOrThrowsException(todoId);
        if((statusEnum.equals(StatusEnum.IN_PROGRESS) &&toDoEntity.getStatus().equals(StatusEnum.OPEN)) ||
                (statusEnum.equals(StatusEnum.CLOSED) &&toDoEntity.getStatus().equals(StatusEnum.IN_PROGRESS))){
            emailSenderService.sendEmail(userRepository.findByToDoEntities(toDoEntity).getEmail(),
                    "Todo's status changed",
                    "Your's ToDo '" + toDoEntity.getTitle() + "' status has been changed from " + toDoEntity.getStatus().name() + " to " + statusEnum.name());
        }
        toDoEntity.setStatus(statusEnum);
        return toDoRepository.save(toDoEntity);
    }

    public ToDoEntity saveTodo(ToDoEntity toDoEntity) {
        return toDoRepository.save(toDoEntity);
    }

    private ToDoEntity findTodoByIdOrThrowsException(Long todoId) {
        return toDoRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("ToDo with ID " + todoId + " not found."));
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public CommentEntity addComment(Comment comment) {
        ToDoEntity toDoEntity = getTodoByTodoId(comment.getTodoId());
        return saveCommentEntity(comment, toDoEntity);
    }

    public void updateStatusAndOptionalAddComment(Long todoId, StatusEnum statusEnum, Optional<Comment> comment) {
        updateTodoOnStatus(todoId, statusEnum);
        if (comment.isPresent()) {
            addComment(comment.get());
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public CommentEntity saveCommentEntity(Comment comment, ToDoEntity toDoEntity) {
        CommentEntity comment1 = new CommentEntity();
        comment1.setComment(comment.getComment());
        comment1 = commentRepository.save(comment1);
        List<CommentEntity> commentList;
        if(Objects.isNull(toDoEntity.getCommentEntities())) {
            commentList = new ArrayList<>();
        } else {
            commentList = toDoEntity.getCommentEntities();
        }
        commentList.add(comment1);
        toDoEntity.setCommentEntities(commentList);
        saveTodo(toDoEntity);
        return comment1;
    }

    public List<CommentEntity> getAllCommentsForTodoId(Long todoId) {
        return findTodoByIdOrThrowsException(todoId).getCommentEntities();
    }

}
