package com.vtb.javacourses.lesson5.task3;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> fruits = new ArrayList<>();

    public float getWeight() {
        if (fruits.size() > 0) {
            return fruits.size() * fruits.get(0).getWeight();
        } else {
            return 0;
        }
    }

    public boolean compare(Box<?> another) {
        return this.getWeight() == another.getWeight();
    }

    public void shiftFruits(Box<T> newBox) {
        for (T fruit : fruits) {
            newBox.addFruit(fruit);
        }
        this.fruits.clear();
    }

    public void addFruit(T newFruit) {
        fruits.add(newFruit);
    }


    public Box() {
    }

    public List<T> getFruits() {
        return fruits;
    }

    public void setFruits(List<T> fruits) {
        this.fruits = fruits;
    }
}
