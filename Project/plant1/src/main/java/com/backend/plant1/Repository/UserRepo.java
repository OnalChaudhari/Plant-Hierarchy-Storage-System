package com.backend.plant1.Repository;

import com.backend.plant1.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    public Optional<User> findByEmail(String email);
}
