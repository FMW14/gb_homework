package com.vtb.javacourses.lesson4;

public class Main {
    public static void main(String[] args) {
        String[][] strings1 = {
                new String[]{"1", "2", "3", "4"},
                new String[]{"1", "2", "3", "4"},
                new String[]{"1", "2", "3", "4"},
                new String[]{"1", "2", "3", "4"}};

        String[][] strings2 = {
                new String[]{"1", "2", "3", "q"},
                new String[]{"1", "2", "3", "4"},
                new String[]{"1", "2", "3", "4"},
                new String[]{"1", "2", "3", "4"}};

        String[][] strings3 = {
                new String[]{"1", "2", "3", "4"},
                new String[]{"1", "2", "3", "4"},
                new String[]{"1", "2", "3", "4"}};

        try {
            System.out.println("sum1 = " + sumArrayElements(strings1));
            System.out.println("sum2 = " + sumArrayElements(strings2));
            System.out.println("sum3 = " + sumArrayElements(strings3));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    public static int sumArrayElements(String[][] arr) throws MyArrayDataException, MyArraySizeException {
        if (arr.length != 4) {
            throw new MyArraySizeException("Wrong array size");
        }

        for (String[] strings : arr) {
            if (strings.length != 4) {
                throw new MyArraySizeException("Wrong subarray size");
            }
        }

        int result = 0;

        for (String[] strings : arr) {
            for (String string : strings) {
                try {
                    result += Integer.parseInt(string);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    throw new MyArrayDataException();
                }
            }
        }
        return result;
    }
}
