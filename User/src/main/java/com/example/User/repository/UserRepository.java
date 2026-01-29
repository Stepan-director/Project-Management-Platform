package com.example.User.repository;

import com.example.User.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    List<User> findByNameAndSurname(String name, String surname);
}
