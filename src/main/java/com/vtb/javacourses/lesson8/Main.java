package com.vtb.javacourses.lesson8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        //Task1
        String str1 = getOneHundredWordStr();
        String resultTask1 = Arrays.stream(str1.split("\\s")).filter(s -> s.length() > 5).collect(Collectors.joining(" ", "Strings with length > 5 symbols: ", ""));
        System.out.println(resultTask1);

        //Task2
        String resultTask2 = Arrays.stream(getTwoDemensionalStrArray()).flatMap(Arrays::stream).map(String::toLowerCase).distinct().collect(Collectors.joining(", ", "Unique words from array: ", "."));
        System.out.println(resultTask2);

        //Task3
        int sum = IntStream.rangeClosed(100, 200).filter(i -> i % 2 == 0).sum();
        System.out.println("Sum even numbers 100-200: " + sum);

        //Task4
        String[] strings = new String[] {"A", "AA", "AAA", "A"};
        int stringsLengthSum = Arrays.stream(strings).mapToInt(String::length).sum();
        System.out.println("Task 3 result: " + stringsLengthSum);

        //Task5
        String[] alphWords = new String[] {"apple", "bread", "cheese", "beer"};
        List<String> alphWordsResult = Arrays.stream(alphWords).sorted().limit(3).collect(Collectors.toList());
        System.out.println("Task 5 result: " + alphWordsResult);
    }

    public static String getOneHundredWordStr() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            stringBuilder.append("AAA ");
            stringBuilder.append("AAAA ");
            stringBuilder.append("AAAAA ");
            stringBuilder.append("AAAAAB ");
            stringBuilder.append("AAAAABB ");
            stringBuilder.append("AAAAABBB ");
            stringBuilder.append("AAAAABBBC ");
            stringBuilder.append("AAAAABBBCC ");
            stringBuilder.append("AAAAABBBCCC ");
            stringBuilder.append("AAAAABBBCCCD ");
        }

        return stringBuilder.toString();
    }

    public static String[][] getTwoDemensionalStrArray() {
        return new String[][]{
                new String[]{"A", "B", "C", "D", "E"},
                new String[]{"c", "D", "E", "F", "G"},
                new String[]{"a", "C", "E", "F", "D"},
                new String[]{"H", "I", "E", "P", "L"},
                new String[]{"R", "W", "F", "J", "G"},
        };
    }
}