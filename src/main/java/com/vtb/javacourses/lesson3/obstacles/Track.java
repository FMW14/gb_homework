package com.vtb.javacourses.lesson3.obstacles;

public class Track extends Obstacle {
    Integer length;

    public Track(Integer length) {
        this.length = length;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public Integer getParameter() {
        return length;
    }
}
