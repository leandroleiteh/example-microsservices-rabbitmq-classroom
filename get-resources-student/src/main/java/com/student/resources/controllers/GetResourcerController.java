package com.student.resources.controllers;

import com.student.resources.dtos.ResponseStudentDto;
import com.student.resources.services.StudentServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/get")
public class GetResourcerController {

    private static final Logger log = LoggerFactory.getLogger(GetResourcerController.class);

    public GetResourcerController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }

    private final StudentServices studentServices;

    @GetMapping("/allStudents")
    public ResponseEntity<List<ResponseStudentDto>> getAllStudents() {

        List<ResponseStudentDto> studentDtoList = studentServices.getAllStudents();
        studentDtoList.forEach(studentDto -> log.info(studentDto.toString()));

        return ResponseEntity.status(HttpStatus.OK).body(studentDtoList);
    }
}
