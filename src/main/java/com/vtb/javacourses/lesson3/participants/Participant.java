package com.vtb.javacourses.lesson3.participants;

import com.vtb.javacourses.lesson3.obstacles.Obstacle;
import com.vtb.javacourses.lesson3.obstacles.Track;
import com.vtb.javacourses.lesson3.obstacles.Wall;

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
