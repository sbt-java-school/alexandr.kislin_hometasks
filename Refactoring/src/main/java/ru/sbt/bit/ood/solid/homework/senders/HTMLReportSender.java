package ru.sbt.bit.ood.solid.homework.senders;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

/**
 * Created by axel on 01.10.2016.
 */
public class HTMLReportSender implements Sender {
    private static final String HOST_NAME = "mail.google.com";

    @Override
    public void sendHTML(String recipients, StringBuilder ReportInHtml) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(HOST_NAME);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(recipients);
            helper.setText(ReportInHtml.toString(), true);
            helper.setSubject("Monthly department salary report");
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Exception into sendReport");
        }
    }
}
