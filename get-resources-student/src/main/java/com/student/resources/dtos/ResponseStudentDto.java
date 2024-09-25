package com.student.resources.dtos;

import com.student.resources.entitys.StudentEntity;

import java.util.UUID;

public record ResponseStudentDto(

        UUID id,
        String name,
        String email
) {

    public ResponseStudentDto(StudentEntity student) {
        this(student.getUuid(), student.getName(), student.getEmail());
    }
}
