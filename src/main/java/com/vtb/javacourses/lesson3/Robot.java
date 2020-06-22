package com.vtb.javacourses.lesson3;

public class Robot implements Participant {
    private String name;
    private final Integer maxHeight;
    private final Integer maxLength;
    private boolean isDroppedOut;

    public Robot(String name, Integer maxHeight, Integer maxLength) {
        this.name = name;
        this.maxHeight = maxHeight;
        this.maxLength = maxLength;
    }

    public Robot() {
        this.maxHeight = 1;
        this.maxLength = 10;
        this.name = "DefRobot";
    }

    @Override
    public boolean run(Integer a) {
        if (a <= maxLength) {
            System.out.println(name + " overcomes " + a + " meters track");
            return true;
        }
        System.out.println(name + " didnt overcomes " + a + " meters track");
        isDroppedOut = true;
        return false;
    }

    @Override
    public boolean jump(Integer a) {
        if (a <= maxHeight) {
            System.out.println(name + " overcomes " + a + " meters wall");
            return true;
        }
        System.out.println(name + " didnt overcomes " + a + " meters wall");
        isDroppedOut = true;
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isDroppedOut() {
        return isDroppedOut;
    }

}
