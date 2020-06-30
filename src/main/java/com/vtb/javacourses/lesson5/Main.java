package com.vtb.javacourses.lesson5;

import com.vtb.javacourses.lesson5.task3.Apple;
import com.vtb.javacourses.lesson5.task3.Box;
import com.vtb.javacourses.lesson5.task3.Orange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Box<Apple> appleBox1 = new Box<>();
        Box<Orange> orangeBox1 = new Box<>();
        Box<Orange> orangeBox2 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();

        appleBox1.addFruit(new Apple());
        appleBox1.addFruit(new Apple());
        appleBox1.addFruit(new Apple());
        orangeBox1.addFruit(new Orange());
        orangeBox1.addFruit(new Orange());
        orangeBox2.addFruit(new Orange());

        System.out.println("AppleBox1 weight = " + appleBox1.getWeight());
        System.out.println("OrangeBox2 weight = " + orangeBox1.getWeight());
        System.out.println("OrangeBox3 weight = " + orangeBox2.getWeight());
        System.out.println(appleBox1.compare(orangeBox1));
        System.out.println(appleBox1.compare(orangeBox2));

        appleBox1.shiftFruits(appleBox2);
        System.out.println("AppleBox1 new weight = " + appleBox1.getWeight());
        System.out.println("AppleBox2 new weight = " + appleBox2.getWeight());
    }

    //Task1
    public static void changeElements(Object[] arr, int first, int second) {
        Object temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    //Task2
    public static <T> List<T> arrayToList(T[] arr) {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, arr);
        return list;
    }
}
