package com.btg.java.sendnotificationemailordems.emailStudent;

import com.btg.java.sendnotificationemailordems.listeners.dto.StudentCreateListenerDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailStudentSend {

    private final JavaMailSender emailSender;

    public EmailStudentSend(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Value("${spring.mail.username}")
    private String fromEmail;
    public static final String NEW_STUDANT_CREATED = "Estudante Cadastrado";
    public static final String UTF_8_ENCODING = "UTF-8";

    @Async
    public void sendEmailNewTransactionDeposit(StudentCreateListenerDto listenerDto) {
        sendEmailStudent(listenerDto);
    }

    private void sendEmailStudent(StudentCreateListenerDto listenerDto) {
        try {

            String text = "Olá " + listenerDto.name() +", seu cadastro foi concluído com sucesso!";

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject(NEW_STUDANT_CREATED);
            helper.setFrom(fromEmail);
            helper.setTo(listenerDto.email());
            helper.setText(text, true);
            emailSender.send(message);
        } catch (MessagingException emailException) {
            throw new RuntimeException("Não foi possível enviar o e-mail: " + emailException.getMessage());
        }
    }

}
