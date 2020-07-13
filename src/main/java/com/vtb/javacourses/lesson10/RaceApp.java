package com.vtb.javacourses.lesson10;


import com.vtb.javacourses.lesson10.stages.Road;
import com.vtb.javacourses.lesson10.stages.Tunnel;

import java.util.concurrent.Semaphore;

public class RaceApp {
    public static void main(String[] args) {
        Race race = new Race(new Road(60), new Tunnel(new Semaphore(2)), new Road(40));
        race.begin();
    }
}
