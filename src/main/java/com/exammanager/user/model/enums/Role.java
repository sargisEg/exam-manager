package com.exammanager.user.model.enums;

public enum Role {
    STUDENT,
    TEACHER,
    ADMIN;

    public static class Of {
        public static final String STUDENT = "STUDENT";
        public static final String TEACHER = "TEACHER";
    }
}
