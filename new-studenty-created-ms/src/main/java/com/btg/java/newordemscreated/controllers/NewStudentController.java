package com.btg.java.newordemscreated.controllers;

import com.btg.java.newordemscreated.dto.StudentNewDto;
import com.btg.java.newordemscreated.services.NewStudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class NewStudentController {

    private final NewStudentService newStudentService;

    public NewStudentController(NewStudentService newStudentService) {
        this.newStudentService = newStudentService;
    }


    @PostMapping("/createStudent")
    public ResponseEntity<StudentNewDto> create(@RequestBody StudentNewDto studentNewDto) {
        return ResponseEntity.ok(newStudentService.createStudenty(studentNewDto));
    }
}
