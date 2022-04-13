package com.kamiroo.todomanager.rest;

import com.kamiroo.todomanager.repo.UserEntity;
import com.kamiroo.todomanager.repo.UserRepository;
import com.kamiroo.todomanager.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public boolean addUser(UserEntity userEntity) {
        return userService.addUser(userEntity);
    }

    @GetMapping("/getUser")
    public List<UserEntity> getUser(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

}
