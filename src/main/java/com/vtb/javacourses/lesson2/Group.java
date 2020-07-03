package com.vtb.javacourses.lesson2;

import java.util.Arrays;

public class Group {
    private String name;
    private Employee[] employees = new Employee[10];

    public Group(String name) {
        this.name = name;
    }

    public void addEmployee(Employee newEmp) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = newEmp;
                break;
            }
        }
    }

    public void removeEmployeeByIndex(Integer a) {
        if (a > 0 && a < employees.length) {
            employees[a] = null;
        }
    }

    public void removeAllEmployees() {
        Arrays.fill(employees, null);
    }

    public void printEmployees() {
        if (employees != null) {
            System.out.println("Group: " + name);
            for (int i = 0; i < employees.length; i++) {
                if (employees[i] != null) {
                    System.out.print(i + ". ");
                    employees[i].printInfo();
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
