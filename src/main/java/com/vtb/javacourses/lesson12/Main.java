package com.vtb.javacourses.lesson12;

import java.sql.*;

public class Main {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement psInsert;

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException {
        DbConnector.connect();
        StudentRepository<Student> studentRepository = new StudentRepository<>(Student.class);
//        Student student1 = new Student("AA", 5);

//        studentRepository.save(student1);
//        Student student2 = studentRepository.findById(1L);
//        System.out.println(student2.toString());

        DbConnector.disconnect();
    }

}
