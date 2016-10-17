package ru.sbt.bit.ood.solid.homework.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by axel on 01.10.2016.
 */
public class JDBCDaoSalaryImpl implements JDBCDao {
    private final Connection connection;
    private final String departmentId;

    /**
     *
     * @param connection - active connection to database
     * @param departmentId - number of department, where workers was finding
     */
    public JDBCDaoSalaryImpl(Connection connection, String departmentId) {
        this.connection = connection;
        this.departmentId = departmentId;
    }

    @Override
    public ResultSet getDataSet(Date dateFrom, Date dateTo) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select emp.id as emp_id, emp.name as amp_name, sum(salary) as salary from employee emp left join" +
                "salary_payments sp on emp.id = sp.employee_id where emp.department_id = ? and" +
                " sp.date >= ? and sp.date <= ? group by emp.id, emp.name");
        ps.setString(0, departmentId);
        ps.setDate(1, new java.sql.Date(dateFrom.getTime()));
        ps.setDate(2, new java.sql.Date(dateTo.getTime()));

        ResultSet result = ps.executeQuery();
        ps.close();
        return result;
    }
}
