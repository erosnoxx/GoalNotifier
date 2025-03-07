package com.erosnox.seeurun.infrastructure.services;

import com.erosnox.seeurun.application.contracts.services.EmailService;
import com.erosnox.seeurun.application.models.dto.EmailDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String FROM;

    @Override
    public void sendEmail(EmailDto email) {

        try {
            var message = mailSender.createMimeMessage();
            var helper = new MimeMessageHelper(message, true);

            helper.setFrom(FROM);
            helper.setTo(email.to());
            helper.setSubject(email.subject());
            helper.setText(email.body(), true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar e-mail: " + e.getMessage(), e);
        }

    }
}
