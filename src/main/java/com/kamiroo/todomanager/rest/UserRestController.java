package com.kamiroo.todomanager.rest;

import com.kamiroo.todomanager.repo.UserEntity;
import com.kamiroo.todomanager.repo.UserRepository;
import com.kamiroo.todomanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserRestController extends AbstractToDoRestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public UserEntity addUser(@Valid @RequestBody UserEntity userEntity) {
        return userService.saveUser(userEntity);
    }

    @GetMapping("/getUser")
    public List<UserEntity> getUser(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    @GetMapping("/getAllUsers")
    public List<UserEntity> getAllUsers() {
        return userRepository.findAllUsers();
    }

    @PostMapping("/deleteUser")
    public List<UserEntity> deleteUser(@RequestBody String firstName, String lastName) {
        return userRepository.deleteByFirstNameAndLastName(firstName, lastName);
    }

}
