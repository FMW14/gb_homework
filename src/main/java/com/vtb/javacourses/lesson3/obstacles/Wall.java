package com.vtb.javacourses.lesson3.obstacles;

public class Wall extends Obstacle {
    Integer height;

    public Wall(Integer height) {
        this.height = height;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public Integer getParameter() {
        return height;
    }
}
