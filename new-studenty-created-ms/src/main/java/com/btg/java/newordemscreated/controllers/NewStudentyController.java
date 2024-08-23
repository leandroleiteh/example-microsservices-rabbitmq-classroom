package com.btg.java.newordemscreated.controllers;

import com.btg.java.newordemscreated.dto.StudentNewDto;
import com.btg.java.newordemscreated.services.NewStudentyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class NewStudentyController {

    private NewStudentyService newStudentyService;

    public NewStudentyController(NewStudentyService newStudentyService) {
        this.newStudentyService = newStudentyService;
    }


    @PostMapping("/createStudenty")
    public ResponseEntity<StudentNewDto> create(@RequestBody StudentNewDto studentNewDto) {
        return ResponseEntity.ok(newStudentyService.createStudenty(studentNewDto));
    }
}
