package com.btg.java.sendnotificationemailordems.listeners;

import com.btg.java.sendnotificationemailordems.emailStudent.EmailStudentSend;
import com.btg.java.sendnotificationemailordems.listeners.dto.StudentCreateListenerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.btg.java.sendnotificationemailordems.configs.RabbitMqConfig.STUDENT_CREATED_QUEUE;

@Component
public class StudentCreatedListener {

    private final EmailStudentSend emailStudentSend;

    private final Logger logger = LoggerFactory.getLogger(StudentCreatedListener.class);

    public StudentCreatedListener(EmailStudentSend emailStudentSend) {
        this.emailStudentSend = emailStudentSend;
    }


    @RabbitListener(queues = STUDENT_CREATED_QUEUE)
    public void listen(@Payload Message<StudentCreateListenerDto> message) {
        logger.info("Email send: {}", message);

        emailStudentSend.sendEmailNewTransactionDeposit(message.getPayload());
    }
}
