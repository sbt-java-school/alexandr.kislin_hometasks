package ru.sbt.bit.ood.solid.homework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sbt.bit.ood.solid.homework.builders.HTMLReportBuilder;
import ru.sbt.bit.ood.solid.homework.dao.JDBCDaoSalaryImpl;
import ru.sbt.bit.ood.solid.homework.senders.Sender;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class SalaryHtmlReportNotifier {

    private Connection connection;
    private Sender sender;
    private HTMLReportBuilder builder;
    private static Logger LOGGER = LogManager.getRootLogger();

    public SalaryHtmlReportNotifier(Connection connection, Sender sender, HTMLReportBuilder builder) {
        this.connection = connection;
        this.sender = sender;
        this.builder = builder;
    }

    public void generateAndSendHtmlSalaryReport(String departmentId, Date dateFrom, Date dateTo, String recipients) {
        if (departmentId != null && dateFrom != null && dateTo != null && recipients != null) {
            try {
            JDBCDaoSalaryImpl impl = new JDBCDaoSalaryImpl(connection, departmentId);
            ResultSet salaryReportFromDB = impl.getDataSet(dateFrom, dateTo);
            sender.sendHTML(recipients,  builder.buildReport(salaryReportFromDB));
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }else{
            throw new IllegalArgumentException("Wrong arguments");
        }
    }
}
