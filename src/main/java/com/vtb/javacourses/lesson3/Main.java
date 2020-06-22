package com.vtb.javacourses.lesson3;

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
