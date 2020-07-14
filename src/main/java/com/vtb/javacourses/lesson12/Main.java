package com.vtb.javacourses.lesson12;

import java.sql.*;

public class Main {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement psInsert;

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException {
//        DbConnector dbConnector = DbConnector.getDbConnector();
        DbConnector.connect();
        Student student1 = new Student("AA", 5);
//        System.out.println(DbConnector.getPreparedStatement("INSERT INTO student (name, score) VALUES ('Bob4', 60);").getMetaData());
//        StringBuilder sb = new StringBuilder();
//        sb.append("ABC");
//        sb.deleteCharAt(sb.length()-1);
//        System.out.println(sb.toString());
//
//        System.out.println(student1.getClass().getSimpleName());

        StudentRepository<Student> studentRepository = new StudentRepository<>(Student.class);
        studentRepository.save(student1);
//        System.out.println(studentRepository);
//        studentRepository.


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
