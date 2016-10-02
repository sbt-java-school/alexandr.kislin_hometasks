package ru.sbt.bit.ood.solid.homework;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import ru.sbt.bit.ood.solid.homework.builders.HTMLReportBuilder;
import ru.sbt.bit.ood.solid.homework.dao.JDBCDaoSalaryImpl;
import ru.sbt.bit.ood.solid.homework.senders.HTMLReportSender;
import ru.sbt.bit.ood.solid.homework.senders.Sender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.whenNew;


@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {Sender.class, HTMLReportSender.class, SalaryHtmlReportNotifier.class, HTMLReportBuilder.class})
public class TestSalaryHtmlReportNotifier {

    private Connection someFakeConnection;
    private ResultSet mockResultSet;
    @Test
    public void test() throws Exception {
        // mock database related stuff
        someFakeConnection = mock(Connection.class);
        mockResultSet = getMockedResultSet(someFakeConnection);
        when(mockResultSet.getString("emp_name")).thenReturn("John Doe", "Jane Dow");
        when(mockResultSet.getDouble("salary")).thenReturn(100.0, 100.0, 50.0, 50.0);
        // mock mail related stuff
        MimeMessageHelper mockMimeMessageHelper = getMockedMimeMessageHelper();
        JDBCDaoSalaryImpl salary = new JDBCDaoSalaryImpl(someFakeConnection, "10" );
        HTMLReportBuilder builder = new HTMLReportBuilder();
        HTMLReportSender sender = new HTMLReportSender();
        // set up parameters
        SalaryHtmlReportNotifier notificator = new SalaryHtmlReportNotifier(someFakeConnection, sender, builder);
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        Date dateFrom = format1.parse("01.01.2014");
        Date dateTo = format1.parse("31.12.2014");
//        LocalDate dateFrom = LocalDate.of(2014, Month.JANUARY, 1);
//        LocalDate dateTo = LocalDate.of(2014, Month.DECEMBER, 31);
        // execute
        notificator.generateAndSendHtmlSalaryReport("10", dateFrom, dateTo, "somebody@gmail.com");
        // verify results
        String expectedReportPath = "src/test/resources/expectedReport.html";
        assertActualReportEqualsTo(mockMimeMessageHelper, expectedReportPath);
    }


    private void assertActualReportEqualsTo(MimeMessageHelper mockMimeMessageHelper, String expectedReportPath) throws MessagingException, IOException {
        ArgumentCaptor<String> messageTextArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockMimeMessageHelper).setText(messageTextArgumentCaptor.capture(), anyBoolean());
        Path path = Paths.get(expectedReportPath);
        String expectedReportContent = new String(Files.readAllBytes(path));
        assertEquals(messageTextArgumentCaptor.getValue(), expectedReportContent);
    }


    private ResultSet getMockedResultSet(Connection someFakeConnection) throws SQLException {
        PreparedStatement someFakePreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(someFakePreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(someFakeConnection.prepareStatement(anyString())).thenReturn(someFakePreparedStatement);
        when(mockResultSet.next()).thenReturn(true, true, false);
        return mockResultSet;
    }

    private MimeMessageHelper getMockedMimeMessageHelper() throws Exception {
        JavaMailSenderImpl mockMailSender = mock(JavaMailSenderImpl.class);
        MimeMessage mockMimeMessage = mock(MimeMessage.class);
        when(mockMailSender.createMimeMessage()).thenReturn(mockMimeMessage);
        whenNew(JavaMailSenderImpl.class).withNoArguments().thenReturn(mockMailSender);
        MimeMessageHelper mockMimeMessageHelper = mock(MimeMessageHelper.class);
        whenNew(MimeMessageHelper.class).withArguments(any(), anyString()).thenReturn(mockMimeMessageHelper);
        return mockMimeMessageHelper;
    }

}
