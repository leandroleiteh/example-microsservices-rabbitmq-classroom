package com.btg.java.savepedido.listeners;

import com.btg.java.savepedido.listeners.dto.StudentyCreateListenerDto;
import com.btg.java.savepedido.services.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.btg.java.savepedido.configs.RabbitMqConfig.STUDENT_CREATED_QUEUE;

@Component
public class StudentCreatedListener {

    private final StudentService studentService;

    private final Logger logger = LoggerFactory.getLogger(StudentCreatedListener.class);

    public StudentCreatedListener(StudentService studentService) {
        this.studentService = studentService;
    }

    @RabbitListener(queues = STUDENT_CREATED_QUEUE)
    public void listen(@Payload Message<StudentyCreateListenerDto> message) {
        logger.info("Message consumed: {}", message);

        studentService.saveStudenty(message.getPayload());
    }
}
