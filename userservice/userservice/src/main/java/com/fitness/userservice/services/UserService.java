package com.fitness.userservice.services;

import com.fitness.userservice.UserRepository;
import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserResponse register(RegisterRequest request) {
        if(repository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email allready exists");

        }


        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());

        User savedUser = repository.save(user);
        UserResponse userResponsee = new UserResponse();
        userResponsee.setId(savedUser.getId());
        userResponsee.setPassword(savedUser.getPassword());
        userResponsee.setEmail(savedUser.getEmail());
        userResponsee.setFirstName(savedUser.getFirstName());
        userResponsee.setLastName(savedUser.getLastName());
        userResponsee.setCreatedAt(savedUser.getCreatedAt());
        userResponsee.setUpdatedAt(savedUser.getUpdatedAt());
        return userResponsee;

    }

    public UserResponse getUserProfile(String userId) {
        User user = repository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not Found"));
        UserResponse userResponsee = new UserResponse();
        userResponsee.setId(user.getId());
        userResponsee.setPassword(user.getPassword());
        userResponsee.setEmail(user.getEmail());
        userResponsee.setFirstName(user.getFirstName());
        userResponsee.setLastName(user.getLastName());
        userResponsee.setCreatedAt(user.getCreatedAt());
        userResponsee.setUpdatedAt(user.getUpdatedAt());
        return userResponsee;

    }
}
