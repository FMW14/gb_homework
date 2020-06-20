package com.vtb.javacourses.lesson2;

public class Employee {
    private String name;
    private String email;
    private Integer age;
    private String position;

    public Employee() {
    }

    public Employee(String name, String email, Integer age, String position) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.position = position;
    }

    public void printInfo() {
        System.out.println("Name: " + name + ", Email: " + email + ", Age: " + age + ", Position: " + position);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
