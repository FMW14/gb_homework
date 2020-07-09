package com.vtb.javacourses.lesson10;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;
    private CyclicBarrier readyCyclicBarrier;
    private CountDownLatch startCdl;
    private CountDownLatch stopCdl;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier cyclicBarrier, CountDownLatch startCountDownLatch, CountDownLatch stopCountDownLatch) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.readyCyclicBarrier = cyclicBarrier;
        this.startCdl = startCountDownLatch;
        this.stopCdl = stopCountDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            startCdl.countDown();
            System.out.println(this.name + " готов");
            readyCyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).overcome(this);
        }

        stopCdl.countDown();
        if (stopCdl.getCount() == CARS_COUNT - 1) {
            System.out.println(this.getName() + " - WIN");
        }

    }
}