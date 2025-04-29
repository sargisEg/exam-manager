package com.exammanager.utils;

import com.exammanager.core.model.entity.Group;

import java.time.LocalDate;

public class GroupUtils {

    public static int getGroupAcademicYear(Group group) {
        return (LocalDate.now().getYear() - group.getStartYear()) + ((LocalDate.now().getMonthValue() < 9 && LocalDate.now().getMonthValue() > 1) ? 0 : 1);
    }

    public static int getGroupSemester(Group group) {
        return getGroupAcademicYear(group) * 2 - ((LocalDate.now().getMonthValue() < 9 && LocalDate.now().getMonthValue() > 1) ? 0 : 1);
    }
}
