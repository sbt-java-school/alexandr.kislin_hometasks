package main.java.ru.sbt.bit.ood.solid.homework.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by axel on 01.10.2016.
 */
public interface JDBCDao {
    ResultSet getDataSet(Date dateFrom, Date dateTo) throws SQLException;
}
