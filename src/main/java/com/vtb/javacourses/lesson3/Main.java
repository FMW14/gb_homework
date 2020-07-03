package com.vtb.javacourses.lesson3;

import com.vtb.javacourses.lesson3.obstacles.Obstacle;
import com.vtb.javacourses.lesson3.obstacles.Track;
import com.vtb.javacourses.lesson3.obstacles.Wall;
import com.vtb.javacourses.lesson3.participants.Cat;
import com.vtb.javacourses.lesson3.participants.Human;
import com.vtb.javacourses.lesson3.participants.Participant;
import com.vtb.javacourses.lesson3.participants.Robot;

public class Main {
    public static void main(String[] args) {

        Participant[] participants1 = {new Human(), new Cat(), new Robot()};

        Obstacle[] obstacles1 = {
                new Wall(1), new Track(3),
                new Wall(3), new Track(4),
                new Wall(4), new Track(8)
        };

        for(Obstacle obs: obstacles1){
            for (Participant participant : participants1){
                if (!participant.isDroppedOut()) {
                    participant.overcome(obs);
                }
            }
            System.out.println("***");
        }
    }
}
