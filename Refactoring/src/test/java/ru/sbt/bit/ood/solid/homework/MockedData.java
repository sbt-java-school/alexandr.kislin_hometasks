package ru.sbt.bit.ood.solid.homework;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

/**
 * Created by axel on 07.10.2016.
 */
public class MockedData {
    public static final String EXPECTED_PATH = "src/test/resources/expectedReport.html";
    public static final SimpleDateFormat dateFormatForSQL = new SimpleDateFormat("dd.MM.yyyy");
    public static final String dataFromInString = "01.01.2014";
    public static final String dataToInString = "31.12.2014";
    private Date dateStartingPeriod;
    private Date dateEndingPeriod;

    public static Date getDateStartingPeriod() throws ParseException {
        return dateFormatForSQL.parse(dataFromInString);
    }

    public static Date getDateEndingPeriod() throws ParseException {
        return dateFormatForSQL.parse(dataToInString);
    }

    public static ResultSet getMockedResultSet(Connection someFakeConnection) throws SQLException {
        PreparedStatement someFakePreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(someFakePreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(someFakeConnection.prepareStatement(anyString())).thenReturn(someFakePreparedStatement);
        when(mockResultSet.next()).thenReturn(true, true, false);
        return mockResultSet;
    }
    public static MimeMessageHelper getMockedMimeMessageHelper() throws Exception {
        JavaMailSenderImpl mockMailSender = mock(JavaMailSenderImpl.class);
        MimeMessage mockMimeMessage = mock(MimeMessage.class);
        when(mockMailSender.createMimeMessage()).thenReturn(mockMimeMessage);
        whenNew(JavaMailSenderImpl.class).withNoArguments().thenReturn(mockMailSender);
        MimeMessageHelper mockMimeMessageHelper = mock(MimeMessageHelper.class);
        whenNew(MimeMessageHelper.class).withArguments(any(), anyString()).thenReturn(mockMimeMessageHelper);
        return mockMimeMessageHelper;
    }
}
