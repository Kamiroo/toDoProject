package com.kamiroo.todomanager.service;

import com.kamiroo.todomanager.repo.UserEntity;
import com.kamiroo.todomanager.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean addUser(UserEntity user) {
        userRepository.save(user);
        return true;
    }

}
