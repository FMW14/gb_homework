package com.vtb.javacourses.lesson14;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        String FILENAME1 = ".\\data1.txt";
        String readed = readFile(FILENAME1);
        System.out.println(readed);

        int result = getNumberOfSequence(readed, "wer");
        System.out.println("Number = " + result);

    }

    //Task1
    public static int getNumberOfSequence(String string, String sequence){
//        char[] stringChars = string.toCharArray();
//        for (int i = 0; i < stringChars.length; i++) {
//            System.out.println(i + " " + stringChars[i]);
//        }
        int index = string.indexOf(sequence);
        int count = 0;
        while (index != -1) {
            count++;
            index = string.indexOf(sequence, index +1);
        }
//        System.out.println("No of sequence in the string is : " + count);

        return count;
    }

    public static String readFile(String fileName) throws IOException {
        StringBuilder resultReader1 = new StringBuilder();
        FileInputStream fstream = new FileInputStream(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        while ((strLine = br.readLine()) != null) {
            resultReader1.append(strLine).append("\n");
        }

        return resultReader1.toString();
    }
}
