package com.kamiroo.todomanager.rest;

import com.kamiroo.todomanager.PriorityEnum;
import com.kamiroo.todomanager.StatusEnum;
import com.kamiroo.todomanager.ToDo;
import com.kamiroo.todomanager.repo.ToDoEntity;
import com.kamiroo.todomanager.repo.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ToDoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserRestController userRestController;

    @Test
    public void addTodoTest() {
        ToDo toDo = new ToDo("tytuł",
                "opis",
                StatusEnum.OPEN,
                PriorityEnum.LOW,
                18L
        );

        String url = "http://localhost:" + port + "/addTodo";


        assertThat(this.testRestTemplate.postForEntity(url, toDo, String.class));
    }

}
