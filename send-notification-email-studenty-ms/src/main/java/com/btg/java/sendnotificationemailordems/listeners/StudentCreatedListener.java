package com.btg.java.sendnotificationemailordems.listeners;

import com.btg.java.sendnotificationemailordems.emailStudenty.EmailStudentySend;
import com.btg.java.sendnotificationemailordems.listeners.dto.StudentyCreateListenerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.btg.java.sendnotificationemailordems.configs.RabbitMqConfig.STUDENT_CREATED_QUEUE;

@Component
public class StudentCreatedListener {

    private final EmailStudentySend emailStudentySend;

    private final Logger logger = LoggerFactory.getLogger(StudentCreatedListener.class);

    public StudentCreatedListener(EmailStudentySend emailStudentySend) {
        this.emailStudentySend = emailStudentySend;
    }


    @RabbitListener(queues = STUDENT_CREATED_QUEUE)
    public void listen(@Payload Message<StudentyCreateListenerDto> message) {
        logger.info("Email sendl: {}", message);

        emailStudentySend.sendEmailNewTransactionDeposit(message.getPayload());
    }
}
