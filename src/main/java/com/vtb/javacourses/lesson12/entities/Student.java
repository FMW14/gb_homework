package com.vtb.javacourses.lesson12.entities;

import com.vtb.javacourses.lesson12.annotations.DbColumn;
import com.vtb.javacourses.lesson12.annotations.DbId;
import com.vtb.javacourses.lesson12.annotations.DbTable;

@DbTable(name = "student")
public class Student {
    @DbId
    private Long id;

    @DbColumn
    private String name;

    @DbColumn
    private Integer score;

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

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}