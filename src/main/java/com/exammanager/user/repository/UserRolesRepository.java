package com.exammanager.user.repository;

import com.exammanager.user.model.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {

    List<UserRoles> findByUserId(String userId);
}
