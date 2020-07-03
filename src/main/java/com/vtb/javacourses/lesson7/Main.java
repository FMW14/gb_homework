package com.vtb.javacourses.lesson7;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
//        getMiddleElementFromArrayListByIndex(100000);
//        getMiddleElementFromLinkedListByIndex(100000);

//        deleteMiddleElementsFromArrayList(100000);
//        deleteMiddleElementsFromLinkedList(100000);

        testMyEntriesAndHashMap();
    }

    //Task1
    public static void getMiddleElementFromArrayListByIndex(int size) {

        System.out.println("Get middle element from ArrayList with size = " + size);
        List<Integer> list = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        long time = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            Integer temp = list.get(size / 2);
        }

        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    //Task1
    public static void getMiddleElementFromLinkedListByIndex(int size) {
        System.out.println("Get middle element from LinkedList with size = " + size);
        List<Integer> list = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        long time = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            Integer temp = list.get(size / 2);
        }

        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    //Task2
    public static void deleteMiddleElementsFromArrayList(int size) {
        System.out.println("Delete middle element from ArrayList with size = " + size);
        List<Integer> list = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        long time = System.currentTimeMillis();

        while (list.size() >= size / 2) {
//            System.out.println(list + ", size = " + list.size());
            list.remove(list.size() / 2);
        }

        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    //Task2
    public static void deleteMiddleElementsFromLinkedList(int size) {
        System.out.println("Delete middle element from LinkedList with size = " + size);
        List<Integer> list = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        long time = System.currentTimeMillis();

        while (list.size() >= size / 2) {
//            System.out.println(list + ", size = " + list.size());
            list.remove(list.size() / 2);
        }

        System.out.println("Time: " + (System.currentTimeMillis() - time));
    }

    //Task3
    public  static void testMyEntriesAndHashMap(){
        List<MyEntry> myEntries = new ArrayList<>(50000);
        Map<Integer, Integer> myMap = new HashMap<>();

        for (int i = 0; i < 50000; i++) {
            int tempRandomInt = ThreadLocalRandom.current().nextInt(0, 100);
            myEntries.add(new MyEntry(i, tempRandomInt));
            myMap.put(i, tempRandomInt);
        }

        System.out.println("MyEntriesTest start");
        long time = System.currentTimeMillis();

        for (int i = 0; i < 100_000; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, 49999);
            Integer tempValue;

            for (int j = 0; j < myEntries.size(); j++) {
                tempValue = myEntries.get(j).getValue();
                if(j==randomIndex)break;
            }
        }

        System.out.println("Time: " + (System.currentTimeMillis() - time));
        System.out.println("MyEntriesTest stop");



        System.out.println("HashMapTest start");
        time = System.currentTimeMillis();

        for (int i = 0; i < 100_000; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, 49999);
            Integer tempValue = myMap.get(randomIndex);
        }

        System.out.println("Time: " + (System.currentTimeMillis() - time));
        System.out.println("MyEntriesTest stop");
    }
}
