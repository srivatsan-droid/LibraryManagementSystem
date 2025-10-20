package com.libraryManagementSystem.codes.repository;

import com.libraryManagementSystem.codes.entity.Role;
import com.libraryManagementSystem.codes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    //Find user by username
    Optional<User> findByUsername(String username);

    //Find user by Email
    Optional<User> findByEmail(String email);

    //Find all Users with a Specific role
    List<User> findByRole(Role role);

    //Check if the Username or email already Exists
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
