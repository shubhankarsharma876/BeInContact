package com.example.demo.service;

import com.example.demo.ExceptionHandling.UserNotFoundException;
import com.example.demo.ExceptionHandling.UsernameAlreadyExistsException;
import com.example.demo.Repository.UserRepository;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User createUser(User user) throws UsernameAlreadyExistsException {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); //Encoding the password
        return userRepository.save(user);
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(STR."No user exists with this user id: \{id}"));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new UserNotFoundException(STR."No user exists with this user id: \{username}"));

    }

    @Override
    public List<User> getAllUser() {

        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {

        if(!userRepository.existsById(user.getId())){
           throw new UserNotFoundException("User with user id:"+ user.getId());
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException("User with user id:"+ id);
        }
        userRepository.deleteById(id);

    }
}
