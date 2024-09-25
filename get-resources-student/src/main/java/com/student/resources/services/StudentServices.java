package com.student.resources.services;

import com.student.resources.dtos.ResponseStudentDto;
import com.student.resources.entitys.StudentEntity;
import com.student.resources.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServices {


    private final StudentRepository studentRepository;

    public StudentServices(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<ResponseStudentDto> getAllStudents() {
        return mapperResponseStudentDto(studentRepository.findAll());
    }

    private List<ResponseStudentDto> mapperResponseStudentDto(List<StudentEntity> students) {
        return students.stream()
                .map(ResponseStudentDto::new)
                .collect(Collectors.toList());
    }
}
