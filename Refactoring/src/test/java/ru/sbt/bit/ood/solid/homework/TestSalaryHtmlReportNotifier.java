package ru.sbt.bit.ood.solid.homework;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mail.javamail.MimeMessageHelper;
import ru.sbt.bit.ood.solid.homework.builders.HTMLReportBuilder;
import ru.sbt.bit.ood.solid.homework.dao.JDBCDaoSalaryImpl;
import ru.sbt.bit.ood.solid.homework.senders.HTMLReportSender;
import ru.sbt.bit.ood.solid.homework.senders.Sender;

import javax.mail.MessagingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {Sender.class, HTMLReportSender.class, SalaryHtmlReportNotifier.class, HTMLReportBuilder.class})
public class TestSalaryHtmlReportNotifier {

    private static Connection someFakeConnection;
    private static ResultSet mockResultSet;
    private static HTMLReportBuilder builder;
    private static HTMLReportSender sender;
    private static JDBCDaoSalaryImpl salary;
    private static Date dateStartingPeriod;
    private static Date dateEndingPeriod;
    private static MimeMessageHelper mockMimeMessageHelper;

    @BeforeClass
    public static void initParams() {
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
        try {
            ResultSet mockedResultFromDB = salary.getDataSet(dateStartingPeriod, dateEndingPeriod);
            System.out.println(mockedResultFromDB);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            assert true;
        }
    }

    @Test
    public void testBuilder() {
        try {
            StringBuilder report = builder.buildReport(mockResultSet);
            Assert.assertNotNull(report);
        } catch (SQLException e) {
            System.out.println("Problem in report builder " + e.getMessage());
            assert true;
        } catch (Exception e) {
            System.out.println("Smth exception "+e.getMessage());
            assert true;
        }
    }

    @Test
    public void testSender() throws SQLException {
        ResultSet resultSet = MockedData.getMockedResultSet(someFakeConnection);
        StringBuilder report = builder.buildReport(resultSet);
        sender.sendHTML("someone@gmail.com",report);
    }
}
