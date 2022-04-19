package com.kamiroo.todomanager.rest;

import com.kamiroo.todomanager.repo.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void addUserTest() throws Exception {
        UserEntity user = new UserEntity(
                "Kamior",
                "Kamil",
                "Dominik",
                "kamil2323.dominik@gmail.com"
        );

        String url = "http://localhost:" + port + "/addUser";

        ResponseEntity<UserEntity> entityResponseEntity = this.testRestTemplate.postForEntity(url, user, UserEntity.class);

        assertThat(entityResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(entityResponseEntity.getBody().getEmail()).isEqualTo(user.getEmail());
    }
}
