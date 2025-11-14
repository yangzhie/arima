package com.yangzhie.arima.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yangzhie.arima.model.User;

// Gateway b/w app and db
// Tasks: querying, saving, updating, and deleting users
@Repository
public interface UserRepository extends JpaRepository<User, Long> { // Data storing, type of ID
    // Custom finder
    Optional<User> findByEmail(String email);
}
