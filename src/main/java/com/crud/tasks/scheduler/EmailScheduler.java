package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.MailCreatorService;
import com.crud.tasks.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    @Autowired
    private MailCreatorService mailCreatorService;

    private static final String SUBJECT = "Tasks: Once a day email";

    private final JavaMailSender javaMailSender;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;

    @Scheduled(cron = "0 0 05 * * *")
    //@Scheduled(fixedDelay = 10000)
    public void sendInformationEmail() {
        long size = taskRepository.count();
        String tasks = size==1 ? " task" : " tasks";
        send(
                new Mail.MailBuilder()
                    .mailTo(adminConfig.getAdminMail())
                    .subject(SUBJECT)
                    .message("Currently in database you got: " + size + tasks)
                    .build()
        );

    }

    private void send(final Mail mail) {
        try {
            javaMailSender.send(createMimeMessage(mail));
        } catch (MailException e) {
        }
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildEmailSchedulerInformationEmail(mail.getMessage()), true);
        };
    }
}
