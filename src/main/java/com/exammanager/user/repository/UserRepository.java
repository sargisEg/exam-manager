package com.exammanager.user.repository;

import com.exammanager.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository<U extends User> extends JpaRepository<U, String> {

    Optional<U> findByEmail(String email);
}
