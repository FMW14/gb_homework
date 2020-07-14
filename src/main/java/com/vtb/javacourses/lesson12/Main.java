package com.vtb.javacourses.lesson12;

import com.vtb.javacourses.lesson12.etities.Student;
import com.vtb.javacourses.lesson12.repo.StudentRepository;

import java.sql.*;

public class Main {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement psInsert;

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException {
        DbConnector.connect();
        StudentRepository<Student> studentRepository = new StudentRepository<>(Student.class);
//        Student student1 = new Student("testname", 10);
        Student student1 = new Student("DROP TABLE student", 5);


        studentRepository.save(student1);
//        Student student2 = studentRepository.findById(1L);
//        System.out.println(student2.toString());

        DbConnector.disconnect();
    }

}
