package ru.sbt.bit.ood.solid.homework;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.whenNew;


@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {Sender.class, HTMLReportSender.class, SalaryHtmlReportNotifier.class, HTMLReportBuilder.class})
public class TestSalaryHtmlReportNotifier {

    private Connection someFakeConnection;
    private ResultSet mockResultSet;
    private HTMLReportBuilder builder;
    private HTMLReportSender sender;
    private JDBCDaoSalaryImpl salary;
//    private SimpleDateFormat dateFormatForSQL = new SimpleDateFormat("dd.MM.yyyy");
//    private final String dataFromInString = "01.01.2014";
//    private final String dataToInString = "31.12.2014";
    private Date dateStartingPeriod;
    private Date dateEndingPeriod;
    private MimeMessageHelper mockMimeMessageHelper;

    @Before
    public void initParams() {
        try {
            dateStartingPeriod = MockedData.getDateStartingPeriod();
            dateEndingPeriod = MockedData.getDateEndingPeriod();
        } catch (ParseException e) {
            System.out.println("Problems with parsing " + e.getMessage());
            assert true;
        }
        someFakeConnection = mock(Connection.class);
        try {
            mockResultSet = MockedData.getMockedResultSet(someFakeConnection);
            when(mockResultSet.getString("emp_name")).thenReturn("John Doe", "Jane Dow");
            when(mockResultSet.getDouble("salary")).thenReturn(100.0, 100.0, 50.0, 50.0);
        } catch (SQLException e) {
            System.out.println("SQL Exception in " + e.getMessage());
            assert true;
        }
        builder = new HTMLReportBuilder();
        sender = new HTMLReportSender();
        salary = new JDBCDaoSalaryImpl(someFakeConnection, "10");
        try {
            mockMimeMessageHelper = MockedData.getMockedMimeMessageHelper();
        } catch (Exception e) {
            System.out.println("Problem with mocking mailsender in " + e.getMessage());
            assert true;
        }
    }

    @Test
    public void testDBRequest() {
        ResultSet mockedResultFromDB = null;
        boolean sqlException = false;
        try {
            mockedResultFromDB = salary.getDataSet(dateStartingPeriod, dateEndingPeriod);
            System.out.println(mockedResultFromDB);
        } catch (SQLException e) {
            sqlException = true;
            System.out.println(e.getMessage());
            assertTrue(sqlException);
        }
    }


    @Test
    public void generatedEqualGood() throws Exception {
        SalaryHtmlReportNotifier notificator = new SalaryHtmlReportNotifier(someFakeConnection, sender, builder);
        notificator.generateAndSendHtmlSalaryReport("10", dateStartingPeriod, dateEndingPeriod, "somebody@gmail.com");
        assertActualReportEqualsTo(mockMimeMessageHelper, MockedData.EXPECTED_PATH);
    }


    private void assertActualReportEqualsTo(MimeMessageHelper mockMimeMessageHelper, String expectedReportPath) throws MessagingException, IOException {
        ArgumentCaptor<String> messageTextArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockMimeMessageHelper).setText(messageTextArgumentCaptor.capture(), anyBoolean());
        Path path = Paths.get(expectedReportPath);
        String expectedReportContent = new String(Files.readAllBytes(path));
        assertEquals(messageTextArgumentCaptor.getValue(), expectedReportContent);
    }
}
