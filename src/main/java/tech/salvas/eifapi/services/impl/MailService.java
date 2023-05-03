package tech.salvas.eifapi.services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import tech.salvas.eifapi.dtos.ActivityDTO;
import tech.salvas.eifapi.dtos.AttendanceDTO;
import tech.salvas.eifapi.dtos.StudentDTO;
import tech.salvas.eifapi.services.IMailService;

import java.util.Locale;


@Service
public class MailService implements IMailService {
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final UserService userService;
    private final ActivityService activityService;
    @Value("${spring.mail.sender}")
    private String senderEmail;

    public MailService(JavaMailSender javaMailSender, TemplateEngine templateEngine, UserService userService, ActivityService activityService) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.userService = userService;
        this.activityService = activityService;
    }

    @Async
    @Override
    public void sendActivitySelection(AttendanceDTO attendance) {
        // Retrieve student and activity infos from their ids
        StudentDTO s = userService.getStudentById(attendance.getStudentId());
        ActivityDTO a = activityService.getById(attendance.getActivityId());

        // Add context variable for thymeleaf template
        Context context = getContext();
        context.setVariable("FullName", s.getFirst_name() + " " + s.getLast_name());
        context.setVariable("FormattedActivityName", a.getCode() + "|" + a.getName());
        String content = templateEngine.process("emails/activity-selection", context);

        System.out.println("Sendging email to: " + s.getEmail());

        // Send email
        sendPlainTextMessage(s.getEmail(), "Voici votre selection d'activité", content);
    }

    private void sendPlainTextMessage(String to, String subject, String content) {
        try {
            MimeMessage mimeMessage = getMimeMessage();
            MimeMessageHelper messageHelper = getMimeMessageHelper(mimeMessage);
            messageHelper.setTo(to);
            messageHelper.setFrom(senderEmail);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            System.out.printf("Email could not be sent to user '%s' : %s\n", to, e.getMessage());
        }
    }

    private MimeMessage getMimeMessage() {
        return this.javaMailSender.createMimeMessage();
    }

    private Context getContext() {
        return new Context(Locale.FRENCH);
    }

    private MimeMessageHelper getMimeMessageHelper(MimeMessage mimeMessage) throws MessagingException {
        return new MimeMessageHelper(mimeMessage, true, "UTF-8");
    }
}
