package com.libraryManagementSystem.codes.service;

import com.libraryManagementSystem.codes.entity.User;
import com.libraryManagementSystem.codes.exception.ResourceNotFoundException;
import com.libraryManagementSystem.codes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //getting all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    //Getting the user by the id
    public Optional<User> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }
    //Create User
    public User createUser(User user) {
        if(userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(user.getEmail())) {
            throw new ResourceNotFoundException("User Already exists");
        }
        return userRepository.save(user);
    }
    //Update Users
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFullName(updatedUser.getFullName());
                    existingUser.setEmail(updatedUser.getEmail());
                    existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
                    existingUser.setRole(updatedUser.getRole());
                    existingUser.setEnabled(updatedUser.isEnabled());
                    existingUser.setAccountNonLocked(updatedUser.isAccountNonLocked());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    //Delete Users
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
