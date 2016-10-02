package ru.sbt.bit.ood.solid.homework.builders;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by axel on 02.10.2016.
 */
public interface Builder {
    StringBuilder buildReport(ResultSet resultSet) throws SQLException;
}
