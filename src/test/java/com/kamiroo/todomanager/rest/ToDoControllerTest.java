package com.kamiroo.todomanager.rest;

import com.kamiroo.todomanager.PriorityEnum;
import com.kamiroo.todomanager.StatusEnum;
import com.kamiroo.todomanager.ToDo;
import com.kamiroo.todomanager.repo.ToDoEntity;
import com.kamiroo.todomanager.repo.UserEntity;
import com.kamiroo.todomanager.service.UserService;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Sql(scripts = "classpath:clean.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ToDoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserRestController userRestController;

    @Autowired
    private ToDoRestController toDoRestController;

    private long createUserWithTask() {
        UserEntity user = new UserEntity("Miro",
                "Konrad",
                "Kowalski",
                "konrad@interia.pl"
        );

        UserEntity addedUser = userRestController.addUser(user);

        ToDo todo = new ToDo("Find todo's for User",
                "Find todo",
                StatusEnum.OPEN,
                PriorityEnum.LOW,
                addedUser.getUserId()
        );

        toDoRestController.addTodo(todo);
        return user.getUserId();
    }

    @Test
    public void addTodoTest() {

        ToDo toDo = new ToDo("Zadanie test",
                "testowe dodanie",
                StatusEnum.OPEN,
                PriorityEnum.LOW,
                19L
        );

        String url = "http://localhost:" + port + "/addTodo";


        assertThat(this.testRestTemplate.postForEntity(url, toDo, String.class));
    }

    @Test
    public void findTodoForUserIdTestReturnCode200() {

//        long userId = createUserWithTask();
        UserEntity user = new UserEntity("Miro",
                "Konrad",
                "Kowalski",
                "konrad@interia.pl"
        );

        UserEntity addedUser = userRestController.addUser(user);

        ToDo todo = new ToDo("Find todo's for User",
                "Find todo",
                StatusEnum.OPEN,
                PriorityEnum.LOW,
                addedUser.getUserId()
        );

        toDoRestController.addTodo(todo);

        String url = "http://localhost:" + port + "/findTodoForUser?userId=" + addedUser.getUserId();

        ResponseEntity<String> result = this.testRestTemplate.getForEntity(url, String.class);

        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void findTodoForUserIdTestReturnCode404() {

        UserEntity user = new UserEntity("Miro",
                "Konrad",
                "Kowalski",
                "konrad@interia.pl"
        );

        UserEntity addedUser = userRestController.addUser(user);

        ToDo todo = new ToDo("Find todo's for User",
                "Find todo",
                StatusEnum.OPEN,
                PriorityEnum.LOW,
                addedUser.getUserId()
        );

        toDoRestController.addTodo(todo);

        Random randomId = new Random();

        String url = "http://localhost:" + port + "/findTodoForUser?userId=" + randomId.nextLong();

        ResponseEntity<String> result = this.testRestTemplate.getForEntity(url, String.class);

        assertEquals(404, result.getStatusCodeValue());
    }

    @Test
    public void getTodoByTodoIdTestReturn200() {

        UserEntity user = new UserEntity("Miro",
                "Konrad",
                "Kowalski",
                "konrad@interia.pl"
        );

        UserEntity addedUser = userRestController.addUser(user);

        ToDo todo = new ToDo("Find todo's by todoId",
                "Find todo",
                StatusEnum.OPEN,
                PriorityEnum.LOW,
                addedUser.getUserId()
        );

        ToDoEntity addedToDo = toDoRestController.addTodo(todo);

        String url = "http://localhost:" + port + "/getTodoByTodoId?todoId="+ addedToDo.getToDoId();

        ResponseEntity<String> result = this.testRestTemplate.getForEntity(url, String.class);

        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void getTodoByTodoIdTestReturn404() {

        UserEntity user = new UserEntity("Miro",
                "Konrad",
                "Kowalski",
                "konrad@interia.pl"
        );

        UserEntity addedUser = userRestController.addUser(user);

        ToDo todo = new ToDo("Find todo's by todoId",
                "Find todo",
                StatusEnum.OPEN,
                PriorityEnum.LOW,
                addedUser.getUserId()
        );

        Random randomId = new Random();

        String url = "http://localhost:" + port + "/findTodoForUser?userId=" + randomId.nextLong();

        ResponseEntity<String> result = this.testRestTemplate.getForEntity(url, String.class);

        assertEquals(404, result.getStatusCodeValue());
    }

    @Test
    public void getTodoForUserWhereStatusIsOpenTestReturn200() {

        UserEntity user = new UserEntity("Miro",
                "Konrad",
                "Kowalski",
                "konrad@interia.pl"
        );

        UserEntity addedUser = userRestController.addUser(user);

        ToDo todo = new ToDo("Find todo's by todoId",
                "Find todo",
                StatusEnum.OPEN,
                PriorityEnum.LOW,
                addedUser.getUserId()
        );

        ToDoEntity addedToDo = toDoRestController.addTodo(todo);

        String url = "http://localhost:" + port + "/getTodoForUserWhereStatus?userId=" + addedUser.getUserId() + "&statusEnum=" + addedToDo.getStatus();

        ResponseEntity<String> result = this.testRestTemplate.getForEntity(url, String.class);

        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void deleteTodoByTodoIdTestReturns200() {

        UserEntity user = new UserEntity("Miro",
                "Konrad",
                "Kowalski",
                "konrad@interia.pl"
        );

        UserEntity addedUser = userRestController.addUser(user);

        ToDo todo = new ToDo("Find todo's by todoId",
                "Find todo",
                StatusEnum.OPEN,
                PriorityEnum.LOW,
                addedUser.getUserId()
        );

        ToDoEntity addedToDo = toDoRestController.addTodo(todo);

        List<ToDoEntity> list = toDoRestController.findTodoForUser(addedUser.getUserId());

        assertTrue(list.size() == 1);

        String url = "http://localhost:" + port + "/deleteTodoByTodoId?todoId=" + addedToDo.getToDoId();

        this.testRestTemplate.delete(url);

        toDoRestController.getTodoByTodoId(addedToDo.getToDoId());

        String url2 = "http://localhost:" + port + "/findTodoForUser?userId=" + addedUser.getUserId();

        ResponseEntity<String> result = this.testRestTemplate.getForEntity(url2, String.class);
    }


    @Test
    public void updateTodoOnTitleAndDescriptionTestReturns() {

        UserEntity user = new UserEntity("Miro",
                "Konrad",
                "Kowalski",
                "konrad@interia.pl"
        );

        UserEntity addedUser = userRestController.addUser(user);

        ToDo todo = new ToDo("Find todo's by todoId",
                "Find todo",
                StatusEnum.OPEN,
                PriorityEnum.LOW,
                addedUser.getUserId()
        );

        String title = "This is new title for already existing ToDo";
        String description = "This is new description for already existing ToDo";

        ToDoEntity addedToDo = toDoRestController.addTodo(todo);

        String url = "http://localhost:" + port + "/updateTodoOnTitleAndDescription?todoId="+ addedToDo.getToDoId() + "&title=" + title + "&description=" + description;


//        ResponseEntity<String> result = this.testRestTemplate.put(url, );

    }

}
