package com.btg.java.savepedido.services;

import com.btg.java.savepedido.entitys.StudentEntity;
import com.btg.java.savepedido.listeners.dto.StudentyCreateListenerDto;
import com.btg.java.savepedido.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository1) {
        this.studentRepository = studentRepository1;
    }

    public void saveStudenty(StudentyCreateListenerDto listenerDto) {

        var studentEnity = new StudentEntity();
        studentEnity.setName(listenerDto.name());
        studentEnity.setEmail(listenerDto.email());
        logger.info("The entity has been persisted: {}", studentEnity);
        studentRepository.save(studentEnity);
    }
}
