package com.kamiroo.todomanager.service;

import com.kamiroo.todomanager.exception.NoContentException;
import com.kamiroo.todomanager.exception.ResourceNotFoundException;
import com.kamiroo.todomanager.repo.UserEntity;
import com.kamiroo.todomanager.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
    }

    public UserEntity saveUser(UserEntity user) {

//        if (user.getFirstName().length() > 0 && user.getLastName().length() > 0 && user.getLogin().length() > 0 && user.getEmail().length() > 0 ){
            return userRepository.save(user);
//        }
//        else {
//            throw new NoContentException("Brak danych");
//        }
    }
}
