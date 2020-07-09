package com.vtb.javacourses.lesson10;


import com.vtb.javacourses.lesson10.stages.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Race {
    public static final int COMPETITORS_COUNT = 4;
    private CountDownLatch startCdl = new CountDownLatch(COMPETITORS_COUNT);
    private CountDownLatch stopCdl = new CountDownLatch(COMPETITORS_COUNT);

    private List<Stage> stages;

    public List<Stage> getStages() {
        return stages;
    }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public void begin() {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Car[] cars = new Car[COMPETITORS_COUNT];
        CyclicBarrier readyCyclicBarrier = new CyclicBarrier(COMPETITORS_COUNT);

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(this, 20 + (int) (Math.random() * 10), readyCyclicBarrier, startCdl, stopCdl);
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        try {
            startCdl.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        try {
            stopCdl.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
