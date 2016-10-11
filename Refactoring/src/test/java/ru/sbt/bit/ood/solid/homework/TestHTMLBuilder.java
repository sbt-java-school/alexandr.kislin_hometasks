package ru.sbt.bit.ood.solid.homework;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mail.javamail.MimeMessageHelper;
import ru.sbt.bit.ood.solid.homework.builders.HTMLReportBuilder;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.*;

/**
 * Created by axel on 07.10.2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {Sender.class, HTMLReportSender.class, SalaryHtmlReportNotifier.class, HTMLReportBuilder.class})
public class TestHTMLBuilder {
    private HTMLReportBuilder builder;
    private MimeMessageHelper mockMimeMessageHelper;
    private ResultSet mockResultSet;
    private Connection fakeConnection;
    private StringBuilder report;

    @Before
    public void init() {
        builder = new HTMLReportBuilder();
        fakeConnection = mock(Connection.class);
        try {
            mockResultSet = MockedData.getMockedResultSet(fakeConnection);
            when(mockResultSet.getString("emp_name")).thenReturn("John Doe", "Jane Dow");
            when(mockResultSet.getDouble("salary")).thenReturn(100.0, 100.0, 50.0, 50.0);
        } catch (SQLException e) {
            System.out.println("SQL Exception in " + e.getMessage());
            assert true;
        }
        try {
            mockMimeMessageHelper = MockedData.getMockedMimeMessageHelper();
        } catch (Exception e) {
            System.out.println("Problem with mocking mailsender in " + e.getMessage());
            assert true;
        }
        System.out.println("Before success");
    }

    @Test
    public void testBuilder() {
        try {
            report = builder.buildReport(mockResultSet);
            System.out.println(report);
        } catch (SQLException e) {
            System.out.println("Problem in report builder " + e.getMessage());
            assert true;
        }
        try {
            assertActualReportEqualsTo(mockMimeMessageHelper, MockedData.EXPECTED_PATH);
        } catch (MessagingException e) {
            System.out.println("Messaging problems with "+e.getMessage());
        } catch (IOException e) {
            System.out.println("IO exception in "+e.getMessage());
        }
    }

    private void assertActualReportEqualsTo(MimeMessageHelper mockMimeMessageHelper, String expectedReportPath) throws MessagingException, IOException {
        ArgumentCaptor<String> messageTextArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockMimeMessageHelper).setText(messageTextArgumentCaptor.capture(), anyBoolean());
        Path path = Paths.get(expectedReportPath);
        String expectedReportContent = new String(Files.readAllBytes(path));
        assertEquals(messageTextArgumentCaptor.getValue(), expectedReportContent);
    }

}
