package com.exammanager.auth.repository;

import com.exammanager.auth.model.entity.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserSessionRepository extends JpaRepository<UserSession, String> {

    List<UserSession> findByUserId(String userId);
    Optional<UserSession> findByUserIdAndId(String userId, String id);
}
