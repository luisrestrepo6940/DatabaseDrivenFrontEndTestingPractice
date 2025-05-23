package co.com.certification.practiceautomatedtesting.utils.various;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class ConexionDatabase {

    public static Connection getConnection(String url) {
        final String MESSAGE_CONNECTION = "Connection to SQLite %s been established. ";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            log.info(String.format(MESSAGE_CONNECTION, "has"));
        } catch (SQLException sqlException) {
            log.error(String.format(MESSAGE_CONNECTION, "has not").concat(String.valueOf(sqlException)));
        }
        return connection;
    }

    public static ResultSet getQuery(Connection connection, String query) {
        final String MESSAGE_CONNECTION = "The query %s been obtained in SQLite. ";
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            log.info(String.format(MESSAGE_CONNECTION, "has"));
        } catch (SQLException sqlException) {
            log.error(String.format(MESSAGE_CONNECTION, "has not").concat(String.valueOf(sqlException)));
        }
        return resultSet;
    }
}
