package com.vtb.javacourses.lesson9;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static final int N = 100_000_000;
    private static final int THRESHOLD = 100_000;
    private static int[] data = new int[N];

    public static void main(String[] args) {

        for (int i = 0; i < data.length; i++) {
            data[i] = ThreadLocalRandom.current().nextInt(0, THRESHOLD);
        }

        //Task1
        //TimeOLD  1674ms  1701ms  1643ms
        //Time 70ms     66ms    106ms   68ms
        long time = System.currentTimeMillis();
        DemoRecursiveTask demoRecursiveTask = new DemoRecursiveTask(data, 0, N);
        int task1Result = ForkJoinPool.commonPool().invoke(demoRecursiveTask);
        System.out.println("max = " + task1Result);
        System.out.println("ForkJoin: " + (System.currentTimeMillis() - time));

        //Task2
        //Time  87ms    85ms    95ms
        time = System.currentTimeMillis();
        int task2Result = Arrays.stream(data).max().getAsInt();
        System.out.println("max = " + task2Result);
        System.out.println("stream: " + (System.currentTimeMillis() - time));

        //Task3
        //Time  23ms    24ms    26ms
        time = System.currentTimeMillis();
        int task3Result = Arrays.stream(data).parallel().max().getAsInt();
        System.out.println("max = " + task3Result);
        System.out.println("parallel stream: " + (System.currentTimeMillis() - time));
    }
}
