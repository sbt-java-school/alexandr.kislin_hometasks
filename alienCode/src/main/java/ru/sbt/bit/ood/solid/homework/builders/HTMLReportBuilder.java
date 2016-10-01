package main.java.ru.sbt.bit.ood.solid.homework.builders;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by axel on 01.10.2016.
 */
public class HTMLReportBuilder {
    private ResultSet resultSet;
    private StringBuilder htmlDoc;
    private Double totalSalary;
    public HTMLReportBuilder(ResultSet results){
        this.resultSet = results;
    }
    public StringBuilder buildReport() throws SQLException {
        htmlDoc = new StringBuilder();
        htmlDoc.append("<html><body><table><tr><td>Employee</td><td>Salary</td></tr>");
        totalSalary = 0.0;
        while (resultSet.next()) {
            // process each row of query results
            htmlDoc.append("<tr>"); // add row start tag
            htmlDoc.append("<td>").append(resultSet.getString("emp_name")).append("</td>"); // appending employee name
            htmlDoc.append("<td>").append(resultSet.getDouble("salary")).append("</td>"); // appending employee salary for period
            htmlDoc.append("</tr>"); // add row end tag
            totalSalary += resultSet.getDouble("salary"); // add salary to total
        }
        htmlDoc.append("<tr><td>Total</td><td>").append(totalSalary).append("</td></tr>");
        htmlDoc.append("</table></body></html>");
        return htmlDoc;
    }
}
