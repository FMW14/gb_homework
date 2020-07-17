package com.vtb.javacourses.lesson6;

import java.util.*;

public class Phonebook {
    private Map<String, Set<Integer>> register = new HashMap<>();

    public void add(String surname, Integer number) {
        Set<Integer> numbers = register.get(surname);
        if (numbers == null){
            numbers = new HashSet<>();
        }
        numbers.add(number);
        register.replace(surname, numbers);
        register.put(surname, numbers);
    }

    public void get(String surname) {
        if (register.containsKey(surname)) {
            for (Integer number : register.get(surname)) {
                System.out.println(surname + ": " + number);
            }
        }
    }
}
