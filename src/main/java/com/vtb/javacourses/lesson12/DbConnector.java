package com.vtb.javacourses.lesson12;

import java.sql.*;

public class DbConnector {
    private static DbConnector dbConnector;

    private static Connection connection;
//    private static Statement statement;
//    private static PreparedStatement preparedStatement;

    public static synchronized DbConnector getDbConnector() {
        if (dbConnector == null) {
            dbConnector = new DbConnector();
        }
        return dbConnector;
    }

    private DbConnector() {

    }

    public static synchronized Statement getStatement() throws SQLException {
        if (connection != null) {
            return connection.createStatement();
        } else {
            throw new RuntimeException("DbConnector not init");
        }

    }

    public static synchronized PreparedStatement getPreparedStatement(String sql) throws SQLException {
        if (connection != null) {
            return connection.prepareStatement(sql);
        } else {
            throw new RuntimeException("DbConnector not init");
        }
    }

    public static Connection getConnection() {
        if (connection!=null){
            return connection;
        } else {
            throw new RuntimeException("DbConnector not init");
        }
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        if (connection == null){
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:data1.db");
        }

//        statement = connection.createStatement();

        // DatabaseMetaData dmd = connection.getMetaData();
    }

    public static void disconnect() {
//        try {
//            if (statement != null) {
//                statement.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            if (preparedStatement != null) {
//                preparedStatement.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
