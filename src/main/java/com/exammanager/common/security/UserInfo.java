package com.exammanager.common.security;


import com.exammanager.user.model.enums.Role;


public record UserInfo(String id, String email, Role role) {
}
