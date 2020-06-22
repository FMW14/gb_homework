package com.vtb.javacourses.lesson3;

public interface Participant {
    boolean run(Integer a);

    boolean jump(Integer a);

    boolean isDroppedOut();

    default boolean overcome(Obstacle obstacle) {
        if (obstacle instanceof Wall) {
            return jump(obstacle.getParameter());
        }
        if (obstacle instanceof Track) {
            return run(obstacle.getParameter());
        }
        return false;
    }
}
