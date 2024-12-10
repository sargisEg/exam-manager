package com.exammanager.common.security;


import com.exammanager.user.model.enums.Role;

import java.util.List;

public record UserInfo(String id, String username, List<Role> roles) {
}
