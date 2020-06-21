package com.vtb.javacourses.lesson2;

public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee("Vasya", "12345", 10, "qwerty");
        Employee employee1 = new Employee("Name1", "qqqqqq", 10, "1111");
        Employee employee2 = new Employee("Name2", "qqqqqq", 10, "1111");

        Group group = new Group("Group1");
        group.addEmployee(employee);
        group.addEmployee(employee1);
        group.addEmployee(employee2);

        group.printEmployees();
        System.out.println("--------");

        group.removeEmployeeByIndex(1);
        group.printEmployees();
        System.out.println("--------");

        group.removeAllEmployees();
        group.printEmployees();
    }
}
