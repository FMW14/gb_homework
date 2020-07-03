package com.vtb.javacourses.lesson8;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String str1 = getOneHundredWordStr();

        //Task1
        String resultTask1 = Arrays.stream(str1.split("\\s")).filter(s -> s.length() > 5).collect(Collectors.joining(" "));
        System.out.println(resultTask1);
    }
    public static String getOneHundredWordStr(){
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
}
