package com.kamiroo.todomanager.rest;

import com.kamiroo.todomanager.Comment;
import com.kamiroo.todomanager.PriorityEnum;
import com.kamiroo.todomanager.StatusEnum;
import com.kamiroo.todomanager.ToDo;
import com.kamiroo.todomanager.exception.ResourceNotFoundException;
import com.kamiroo.todomanager.repo.CommentEntity;
import com.kamiroo.todomanager.repo.ToDoEntity;
import com.kamiroo.todomanager.repo.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Sql(scripts = "classpath:clean.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class CommentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserRestController userRestController;

    @Autowired
    private ToDoRestController toDoRestController;

    @Autowired
    private CommentRestController commentRestController;


    @Test
    public void addCommentTestSuccessful() {

        UserEntity user = new UserEntity(
                "Romik",
                "Konrad",
                "Kowalski",
                "konrad.kowalski01@gmail.com"
        );

        UserEntity addedUser = userRestController.addUser(user);

        ToDo toDo = new ToDo(
                "Adding comment",
                "I want to add new comment",
                StatusEnum.OPEN,
                PriorityEnum.LOW,
                addedUser.getUserId()
        );

        ToDoEntity addedToDo = toDoRestController.addTodo(toDo);

        Comment comment = new Comment(
                addedToDo.getToDoId(),
                "This is a comment for a Adding comment ToDo"
        );

        String url = "http://localhost:" + port + "/addComment";

        ResponseEntity<String> result = this.testRestTemplate.withBasicAuth("admin", "admin1").postForEntity(url, comment, String.class);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void addCommentTestFail() {

        UserEntity user = new UserEntity(
                "Romik",
                "Konrad",
                "Kowalski",
                "konrad.kowalski01@gmail.com"
        );

        UserEntity addedUser = userRestController.addUser(user);

        ToDo toDo = new ToDo(
                "Adding comment",
                "I want to add new comment",
                StatusEnum.OPEN,
                PriorityEnum.LOW,
                addedUser.getUserId()
        );

        ToDoEntity addedToDo = toDoRestController.addTodo(toDo);

        Long badTodoId = addedToDo.getToDoId() + 1;

        Comment comment = new Comment(
                badTodoId,
                "This is a comment for a Adding comment ToDo"
        );

        assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> toDoRestController.addComment(comment));
    }

}
