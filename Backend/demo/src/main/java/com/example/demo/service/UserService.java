package com.example.demo.service;

import com.example.demo.ExceptionHandling.UsernameAlreadyExistsException;
import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user) throws UsernameAlreadyExistsException;
    User getUserById(String id);
    User getUserByUsername(String username);
    List<User> getAllUser();
    User updateUser(User user);
    void deleteUser(String id);

}
