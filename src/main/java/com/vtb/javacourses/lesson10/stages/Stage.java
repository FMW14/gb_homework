package com.vtb.javacourses.lesson10.stages;


import com.vtb.javacourses.lesson10.Car;

public abstract class Stage {
    int length;
    String description;

    public String getDescription() {
        return description;
    }

    public abstract void overcome(Car c);
}
