package com.vtb.javacourses.lesson2;

public class Group {
    private String name;
    private Employee[] employees = new Employee[10];

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public void addEmployee(Employee emp) {

    }

    public void removeEmployeeByIndex(Integer a) {

    }

    public void removeAllEmployees() {

    }

    public void printEmployees() {
        for (Employee employee : employees) {
            employee.printInfo();
        }
    }
}
