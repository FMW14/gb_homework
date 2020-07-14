package com.vtb.javacourses.lesson12;

@DbTable(name = "student")
public class Student {
    @DbId
    Long id;

    @DbColumn
    String name;

    @DbColumn
    Integer score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Student(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    public Student() {
    }
}
