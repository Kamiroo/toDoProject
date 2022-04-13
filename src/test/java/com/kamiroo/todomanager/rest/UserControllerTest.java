package com.kamiroo.todomanager.rest;

import com.kamiroo.todomanager.repo.UserEntity;
import com.kamiroo.todomanager.repo.UserRepository;
import com.kamiroo.todomanager.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserRestController userRestController;

    @Autowired
    private UserRepository userRepository;

    private UserService userService;

    @Test
    @Transactional
    public void addUserTest() throws Exception {
           UserEntity user = new UserEntity(
                   "Kamior",
                   "Kamil",
                   "Dominik",
                   "kamil23.dominik@gmail.com"
           );

           UserEntity savedUser = userRepository.save(user);

           assertEquals(user, savedUser);
    }
}
