package com.btg.java.newordemscreated.services;

import com.btg.java.newordemscreated.dto.StudentNewDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.btg.java.newordemscreated.configs.RabitMqConfig.STUDENT_CREATED_QUEUE;

@Service
public class NewStudentService {

    private static final Logger logger = LoggerFactory.getLogger(NewStudentService.class);


    private static RabbitTemplate rabbitTemplate;

    public NewStudentService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public StudentNewDto createStudenty(StudentNewDto studentNewDto) {
        rabbitTemplate.convertAndSend(STUDENT_CREATED_QUEUE, studentNewDto);
        logger.info("The data recive: {}", studentNewDto);

        return studentNewDto;
    }
}
