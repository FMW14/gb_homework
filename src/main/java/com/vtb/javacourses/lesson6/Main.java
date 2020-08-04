package com.vtb.javacourses.lesson6;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String[] words = {"word", "another", "word", "more", "words", "more", "words", "need", "more", "words"};
        analyseArray(words);

        Phonebook phonebook = new Phonebook();
        phonebook.add("Petrov", 123456789);
        phonebook.add("Petrov", 12345678);
        phonebook.add("Ivanov", 12345678);

        phonebook.get("Petrov");

    }

    //Task1
    public static void analyseArray(String[] arr) {
        List<String> list = Arrays.stream(arr).collect(Collectors.toList());

        Map<String, Integer> words = new HashMap<>();
        for (String s : list) {
            if (!words.containsKey(s)) {
                words.put(s, 1);
            } else {
                words.put(s, words.get(s) + 1);
            }
        }

        System.out.println("Unique words: " + list.stream().distinct().collect(Collectors.toList()));
        System.out.println("Words count: " + words);
    }
}
