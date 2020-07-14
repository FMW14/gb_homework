package com.vtb.javacourses.lesson12;

import java.sql.*;

public class Main {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement psInsert;

    public static void main(String[] args) {
        DbConnector dbConnector = DbConnector.getDbConnector();
        Student student1 = new Student();

        ;

        StudentRepository<Student> studentRepository = new StudentRepository<>(Student.class);
//        studentRepository.

        try {
//            connect();
//            System.out.println(connection.getMetaData());
//            rollbackEx();

//            addDataPreparedStatementEx();
//            rollbackEx();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        DbConnector.disconnect();
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:data1.db");
        stmt = connection.createStatement();
        // DatabaseMetaData dmd = connection.getMetaData();
    }

    public static void disconnect() {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (psInsert != null) {
                psInsert.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
