package com.vtb.javacourses.lesson12;

import com.vtb.javacourses.lesson12.entities.Student;
import com.vtb.javacourses.lesson12.repo.StudentRepository;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException {
        DbConnector.connect();
        StudentRepository<Student> studentRepository = new StudentRepository<>(Student.class);
//        Student student1 = new Student("testname", 10);
        Student student1 = new Student("testname", 5);

        System.out.println(studentRepository.findById(1L));
        System.out.println(studentRepository.findById(2L));
        System.out.println(studentRepository.findAll());

        studentRepository.save(student1);
//        Student student2 = studentRepository.findById(1L);
//        System.out.println(student2.toString());

        DbConnector.disconnect();

//        Class c = Cat.class;
//        for (Field f : c.getDeclaredFields()) {
//            String fieldName = f.getName();
//            String setterName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
//            Method m = c.getDeclaredMethod(setterName, f.getType());
//            System.out.println(setterName);
//        }
    }

}
