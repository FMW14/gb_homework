package com.vtb.javacourses.lesson5.task3;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> fruits = new ArrayList<>();

    public float getWeight() {
        if (!fruits.isEmpty()) {
            return fruits.size() * fruits.get(0).getWeight();
        } else {
            return 0;
        }
    }

    public boolean compare(Box<?> another) { //норм сравнение сделать
        return Math.abs(this.getWeight() - another.getWeight()) < 0.0001;

//        return this.getWeight() == another.getWeight();
    }

    public void shiftFruits(Box<T> newBox) { // проверить на себя
        if (this != newBox){
            for (T fruit : fruits) {
                newBox.addFruit(fruit);
            }
            this.fruits.clear();
        }
    }

    public void addFruit(T newFruit) {
        fruits.add(newFruit);
    }
}
