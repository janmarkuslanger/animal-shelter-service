package com.janmarkuslanger.animalshelterservice.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    public EmailServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendSimpleEmailSendsEmailWithCorrectDetails() {
        String to = "test@example.com";
        String subject = "Test Subject";
        String text = "Test Text";

        emailService.sendSimpleEmail(to, subject, text);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        verify(mailSender, times(1)).send(message);
    }

    @Test
    void sendSimpleEmailThrowsExceptionWhenMailSenderFails() {
        String to = "test@example.com";
        String subject = "Test Subject";
        String text = "Test Text";

        doThrow(new RuntimeException("Mail server error")).when(mailSender).send(any(SimpleMailMessage.class));

        assertThatThrownBy(() -> emailService.sendSimpleEmail(to, subject, text))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Mail server error");
    }
}