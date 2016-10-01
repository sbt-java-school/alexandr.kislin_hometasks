package main.java.ru.sbt.bit.ood.solid.homework.senders;

/**
 * Created by axel on 01.10.2016.
 */
public class HTMLReportSender {
    private static final String HOST_NAME = "mail.google.com";
    public static void sendHTMLtoGMail(String recipients, StringBuilder ReportInHtml) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(HOST_NAME);
        // we're going to use google mail to send this message
        // construct the message
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(recipients);
        // setting message text, last parameter 'true' says that it is HTML format
        helper.setText(ReportInHtml.toString(), true);
        helper.setSubject("Monthly department salary report");
        // send the message
        mailSender.send(message);
    }
}
